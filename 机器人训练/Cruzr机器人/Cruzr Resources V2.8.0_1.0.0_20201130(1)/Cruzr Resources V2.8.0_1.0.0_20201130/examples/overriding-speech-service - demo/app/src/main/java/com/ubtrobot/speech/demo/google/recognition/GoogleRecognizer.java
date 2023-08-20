package com.ubtrobot.speech.demo.google.recognition;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechGrpc;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1.StreamingRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;
import com.google.protobuf.ByteString;
import com.ubtrobot.async.Consumer;
import com.ubtrobot.async.ListenerList;
import com.ubtrobot.speech.AudioRecordListener;
import com.ubtrobot.speech.demo.google.token.GoogleUserCredentials;
import com.ubtrobot.speech.demo.google.token.TokenAuthorizer;
import com.ubtrobot.speech.demo.google.token.TokenUtil;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.internal.DnsNameResolverProvider;
import io.grpc.okhttp.OkHttpChannelProvider;
import io.grpc.stub.StreamObserver;

public class GoogleRecognizer {

    private static final String TAG = "GoogleRecognizer";

    private static final int[] SAMPLE_RATE_CANDIDATES = new int[]{16000, 11025, 22050, 44100};
    //1500:63.5dB 1000:60dB 600:55.6dB 500:54dB 300:49.5dB 100:40dB 50:34dB
    private static final int AMPLITUDE_THRESHOLD = 1500;//vad Detection threshold

    // If the pause interval exceeds this time, the last recognition is considered to be over.
    private static final long VOICE_MUTE_TIMEOUT_MS = 2 * 1000;// Silent detection
    private static final long VOLUME_REPORT_INTERVAL_MS = 1000;// Interval time for reporting volume changes

    private static final int CHANNEL = AudioFormat.CHANNEL_IN_MONO;
    private static final int ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private boolean mVadDetected;
    private int mErrorCount = 0;
    private int mSampleRateInHz;
    private long mLastReportTime = 0;
    private long mRecognizingTimeoutMs;
    private long mRecognizingStartTime = Long.MAX_VALUE;
    private long mLastVoiceHearTime = Long.MAX_VALUE;

    private String mResponseTag = "";
    private boolean mFinal = false;
    private volatile String mLastText = "";

    private final byte[] requestObserverLock = new byte[0];
    private final ListenerList<RecognizeListener> mListenerList;

    private TokenAuthorizer mTokenAuthorizer;
    private volatile AndroidRecorder mAndroidRecorder;
    private volatile SpeechGrpc.SpeechStub mSpeechGrpcApi;
    private volatile StreamObserver<StreamingRecognizeRequest> mRequestObserver;
    private volatile StreamObserver<StreamingRecognizeResponse> mResponseObserver;

    private Executor executor = Executors.newSingleThreadExecutor();

    public GoogleRecognizer(TokenAuthorizer tokenAuthorizer) {
        if (null == tokenAuthorizer) {
            throw new IllegalArgumentException("Argument tokenAuthorizer is null.");
        }
        this.mTokenAuthorizer = tokenAuthorizer;
        mListenerList = new ListenerList<>(new Handler(Looper.getMainLooper()));

        initAndroidRecorder();

        openChannel();


    }

    private boolean initAndroidRecorder() {
        Executor executor = Executors.newSingleThreadExecutor();
        mAndroidRecorder = AndroidRecorder.getInstance();
        mAndroidRecorder.registerRecordListener(mRecordListener, executor, null);

        for (int sampleRateInHz : SAMPLE_RATE_CANDIDATES) {
            if (0 == mAndroidRecorder.initRecorder(sampleRateInHz, CHANNEL, ENCODING, MediaRecorder.AudioSource.MIC)) {
                mSampleRateInHz = sampleRateInHz;
                Log.i(TAG, "initAndroidRecorder success SampleRateInHz = " + mSampleRateInHz);
                return true;
            }
        }

        Log.e(TAG, "initAndroidRecorder fail.");
        return false;
    }

    private AudioRecordListener mRecordListener = new AudioRecordListener() {
        @Override
        public void onRecord(byte[] data, int size) {
            if (size <= 0) {
                Log.e(TAG, "size <= 0");
                return;
            }

            long now = System.currentTimeMillis();

            if (now - mLastReportTime > VOLUME_REPORT_INTERVAL_MS) {
                reportVolumeChange(data, size);
                mLastReportTime = System.currentTimeMillis();
            }

            if (mRecognizingTimeoutMs > 0 && mRecognizingStartTime != Long.MAX_VALUE
                    && (now - mRecognizingStartTime) > mRecognizingTimeoutMs) {
                Log.e(TAG, "recognizing timeout > " + mRecognizingTimeoutMs);
                mAndroidRecorder.stop();
                reportTimeout();
                return;
            }

            //Check if the sound is valid
            if (isHearingVoice(data, size)) {
                if (Long.MAX_VALUE == mLastVoiceHearTime) {
                    beginRecognizing();
                }

                doingRecognizing(data, size);
                mLastVoiceHearTime = now;
                mVadDetected = true;
                return;
            }

            if (mVadDetected && Long.MAX_VALUE != mLastVoiceHearTime) {
                doingRecognizing(data, size);

                if (now - mLastVoiceHearTime > VOICE_MUTE_TIMEOUT_MS) {
                    Log.i(TAG, "voice mute time > " + VOICE_MUTE_TIMEOUT_MS);
                    endRecognizing();
                    mVadDetected = false;
                }
            }
        }

        private boolean isHearingVoice(byte[] buffer, int size) {
            for (int i = 0; i < size - 1; i += 2) {
                // The buffer has LINEAR16 in little endian.
                int s = buffer[i + 1];
                if (s < 0) s *= -1;
                s <<= 8;
                s += Math.abs(buffer[i]);
                if (s > AMPLITUDE_THRESHOLD) {
                    return true;
                }
            }

            return false;
        }
    };

    private void reportVolumeChange(final byte[] data, final int size) {
        synchronized (mListenerList) {
            mListenerList.forEach(new Consumer<RecognizeListener>() {
                @Override
                public void accept(RecognizeListener listener) {
                    listener.onVolume(calculateVolume(data, size));
                }
            });
        }
    }

    private int calculateVolume(byte[] data, int size) {
        long valueSum = 0;

        for (int value : data) {
            valueSum += value * value;
        }

        return (int) (10 * Math.log10(valueSum / (double) size));
    }

    private void reportTimeout() {
        mRecognizingStartTime = Long.MAX_VALUE;
        synchronized (mListenerList) {
            mListenerList.forEach(new Consumer<RecognizeListener>() {
                @Override
                public void accept(RecognizeListener listener) {
                    listener.onTimeout();
                }
            });
        }
    }

    private void beginRecognizing() {
        Log.i(TAG, "beginRecognizing");
        if (mSpeechGrpcApi == null) {
            Log.e(TAG, "API not ready. Ignoring the request.");
            notifyError();
            return;
        }

        synchronized (requestObserverLock) {
            try {
                mLastText = "";
                Log.i(TAG, "create mRequestObserver begin");
                mRequestObserver = mSpeechGrpcApi.streamingRecognize(mResponseObserver);
                Log.i(TAG, "create mRequestObserver success");
            } catch (Exception e) {
                Log.e(TAG, "create mRequestObserver failed");
                notifyError();
                return;
            }

            mRequestObserver.onNext(StreamingRecognizeRequest.newBuilder()
                    .setStreamingConfig(StreamingRecognitionConfig.newBuilder()
                            .setConfig(RecognitionConfig.newBuilder()
                                    .setLanguageCode(getDefaultLanguageCode())
                                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                                    .setSampleRateHertz(mSampleRateInHz)
                                    .build())
                            .setInterimResults(true)
                            .setSingleUtterance(true)
                            .build())
                    .build());
            Log.i(TAG, "startRecognize end:" + mRequestObserver);
        }
    }

    private void endRecognizing() {
        Log.i(TAG, "endRecognizing");
        mLastVoiceHearTime = Long.MAX_VALUE;

        synchronized (requestObserverLock) {
            if (null != mRequestObserver) {
                mRequestObserver.onCompleted();
                mRequestObserver = null;
            }
        }
    }

    private void doingRecognizing(byte[] data, final int size) {
        synchronized (requestObserverLock) {
            if (mRequestObserver == null) {
                Log.i(TAG, "mRequestObserver==null");
                return;
            }

            mRequestObserver.onNext(StreamingRecognizeRequest.newBuilder()
                    .setAudioContent(ByteString.copyFrom(data, 0, size))
                    .build());
        }
    }

    private String getDefaultLanguageCode() {
        Locale locale = Locale.getDefault();
        StringBuilder language = new StringBuilder(locale.getLanguage());
        String country = locale.getCountry();
        if (!TextUtils.isEmpty(country)) {
            language.append("-");
            language.append(country);
        }
        return language.toString();
    }

    public void addListener(@NonNull RecognizeListener listener) {
        synchronized (mListenerList) {
            mListenerList.register(listener);
        }
    }

    public void removeListener(@NonNull RecognizeListener listener) {
        synchronized (mListenerList) {
            mListenerList.unregister(listener);
        }
    }

    public synchronized void startRecognizing(long timeoutMs) {
        Log.i(TAG, "call startVoiceRecorder()");
        mRecognizingTimeoutMs = timeoutMs;
        createResponseObserver();
        mAndroidRecorder.start();
        mRecognizingStartTime = System.currentTimeMillis();
    }

    public synchronized void stopRecognizing() {
        Log.i(TAG, "call stopVoiceRecorder()");
        mResponseTag = "";
        stopVoiceRecorder();
        endRecognizing();
    }

    private void stopVoiceRecorder() {
        mLastVoiceHearTime = Long.MAX_VALUE;
        if (null != mAndroidRecorder) {
            mAndroidRecorder.stop();
        }
    }

    private void createResponseObserver() {
        mResponseObserver = new StreamObserver<StreamingRecognizeResponse>() {
            @Override
            public void onNext(StreamingRecognizeResponse response) {
                String text = "";
                boolean isFinal = false;
                if (response.getResultsCount() > 0) {
                    StreamingRecognitionResult result = response.getResults(0);
                    isFinal = result.getIsFinal();
                    if (result.getAlternativesCount() > 0) {
                        SpeechRecognitionAlternative alternative = result.getAlternatives(0);
                        text = alternative.getTranscript();
                    }
                }

                if (!TextUtils.isEmpty(text)) {
                    onRecognitionEnd(text, isFinal);
                } else {
                    Log.e(TAG, "text == null");
                }
            }

            @Override
            public void onError(Throwable t) {
                if (!this.toString().equals(mResponseTag)) {
                    Log.i(TAG, "StreamObserver onError : not equals");
                    return;
                }
                Log.e(TAG, "Error calling the API." + t.getMessage());
                onRecognitionError(t);
            }

            @Override
            public void onCompleted() {
                if (!this.toString().equals(mResponseTag)) {
                    Log.i(TAG, "StreamObserver onCompleted: not equals");
                    return;
                }
                Log.i(TAG, "API completed.");
                onRecognitionCompleted();
            }
        };

        mResponseTag = mResponseObserver.toString();
    }

    private void onRecognitionEnd(String text, final boolean isFinal) {
        Log.i(TAG, "RecognizedResult:" + text + "| isFinal:" + isFinal);
        mErrorCount = 0;
        mLastText = checkInvalidStr(text);
        mFinal = isFinal;

        if (!isFinal) {
            return;
        }

        synchronized (mListenerList) {
            mListenerList.forEach(new Consumer<RecognizeListener>() {
                @Override
                public void accept(RecognizeListener listener) {
                    listener.onResult(mLastText);
                }
            });
        }
    }

    private void onRecognitionError(Throwable t) {
        boolean ignoreError = false;
        if ("UNAVAILABLE".equals(t.getMessage())) {
            Log.e(TAG, "check if the network access google server");
        } else if ("UNKNOWN".equals(t.getMessage())) {
            mErrorCount++;
            openChannel();//Try to restart the downstream channel
            Log.e(TAG, "obtain UNKNOWN ERROR counts:：" + mErrorCount);
        } else if (t.getMessage() != null && t.getMessage().contains("OUT_OF_RANGE:")) {
            ignoreError = true;
        }
        //OUT_OF_RANGE: Audio data is being streamed too slow. Please stream audio data approximately at real time.
        Log.e(TAG, "onError:" + t.getMessage());

        if (!ignoreError) {
            notifyError();
        }
    }

    private void onRecognitionCompleted() {
        if (!mFinal && !TextUtils.isEmpty(mLastText)) {
            Log.i(TAG, "onCompleted push last false text:" + mLastText);
            mListenerList.forEach(new Consumer<RecognizeListener>() {
                @Override
                public void accept(RecognizeListener listener) {
                    listener.onResult(mLastText);
                }
            });
        }
    }

    private String checkInvalidStr(String source) {
        if (!TextUtils.isEmpty(source)) {
            char c = source.charAt(source.length() - 1);
            if (Character.valueOf(',').equals(c)) {
                Log.i(TAG, "text has ,");
                source = source.substring(0, source.length() - 1);
            }
        }

        return source;
    }

    private void notifyError() {
        synchronized (mListenerList) {
            mListenerList.forEach(new Consumer<RecognizeListener>() {
                @Override
                public void accept(RecognizeListener listener) {
                    listener.onError(-1);
                }
            });
        }
    }

    public void release() {
        Log.i(TAG, "call the release");
        releaseChannel();

        if (null != mAndroidRecorder) {
            mAndroidRecorder.release();
            mAndroidRecorder.unregisterRecordListener(mRecordListener);
        }
    }

    private void openChannel() {
        releaseChannel();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ManagedChannel channel = new OkHttpChannelProvider()
                        .builderForAddress("speech.googleapis.com", 443)
                        .nameResolverFactory(new DnsNameResolverProvider())
                        .intercept(new GoogleCredentialsInterceptor(
                                new GoogleUserCredentials(mTokenAuthorizer)
                                        .createScoped(TokenUtil.SCOPE)))
                        .build();

                mSpeechGrpcApi = SpeechGrpc.newStub(channel);
                Log.d(TAG, "mSpeechGrpcApi =" + mSpeechGrpcApi);
            }
        });

    }

    private void releaseChannel() {
//        if (mSpeechGrpcApi == null) {
//            Log.i(TAG, "mSpeechGrpcApi is already null.");
//            return;
//        }
//
//        ManagedChannel channel = (ManagedChannel) mSpeechGrpcApi.getChannel();
//        if (channel != null && !channel.isShutdown()) {
//            try {
//                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                Log.e(TAG, "Error shutting down the gRPC channel." + e.getMessage());
//            }
//        }
//
//        mSpeechGrpcApi = null;
    }
}

