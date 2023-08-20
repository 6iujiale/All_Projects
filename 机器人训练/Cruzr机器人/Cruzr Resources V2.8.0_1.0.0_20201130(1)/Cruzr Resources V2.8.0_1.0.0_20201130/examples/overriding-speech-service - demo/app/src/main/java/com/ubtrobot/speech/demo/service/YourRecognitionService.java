package com.ubtrobot.speech.demo.service;

import android.text.TextUtils;
import android.util.Log;

import com.ubtrobot.service.ModuleDestroyable;
import com.ubtrobot.speech.AbstractRecognizer;
import com.ubtrobot.speech.RecognitionException;
import com.ubtrobot.speech.RecognitionOption;
import com.ubtrobot.speech.RecognitionProgress;
import com.ubtrobot.speech.RecognitionResult;
import com.ubtrobot.speech.demo.google.recognition.GoogleRecognizer;
import com.ubtrobot.speech.demo.google.recognition.RecognizeListener;
import com.ubtrobot.speech.demo.google.token.TokenAuthorizer;

/**
 * This class is used to implement your own voice broadcast function.
 * For more help, please check README.MD
 */
public class YourRecognitionService extends AbstractRecognizer implements ModuleDestroyable {

    private static final String TAG = "YourRecognitionService";

    private static final long MAX_SINGLE_SPEECH_LENGTH_MS = 60 * 1000;//Single recognition Maximum time
    private static final long MIN_SINGLE_SPEECH_LENGTH_MS = 3 * 1000;//Single recognition minimum time
    private static final long DEF_SINGLE_SPEECH_LENGTH_MS = 12 * 1000;//Single recognition default time

    private GoogleRecognizer mGoogleAsr;

    private boolean mRelease = false;
    private boolean mIsRecognizing = false;
    private boolean mSingleMode;

    public YourRecognitionService(TokenAuthorizer mTokenAuthorizer) {
        mGoogleAsr = new GoogleRecognizer(mTokenAuthorizer);
        mGoogleAsr.addListener(mRecognizeListener);
    }

    private RecognizeListener mRecognizeListener = new RecognizeListener() {

        @Override
        public void onResult(String txtResult) {
            Log.i(TAG, "google recognize result: " + txtResult);
            txtResult = txtResult.trim();
            if (mIsRecognizing) {
                if (!mSingleMode) {
                    if (!TextUtils.isEmpty(txtResult) && txtResult.length() > 1) {
                        reportRecognizingProgress(new RecognitionProgress.Builder(
                                RecognitionProgress.PROGRESS_RECOGNITION_TEXT_RESULT)
                                .setTextResult(txtResult)
                                .build());
                    }
                } else {
                    // Single recognition, stop the recognizing
                    Log.i(TAG, "stop GoogleAsr task");
                    stopRecognizing();
                    resolveRecognizing(new RecognitionResult.Builder(txtResult).build());
                }
            }
        }

        @Override
        public void onError(int error) {
            Log.i(TAG, "google error code:" + error);
            if (mSingleMode) {
                stopRecognizing();
                rejectRecognizing(new RecognitionException(RecognitionException.CODE_INTERNAL_ERROR, "google speech error!"));
            }
        }

        @Override
        public void onVolume(int volume) {
            reportRecognizingProgress(new RecognitionProgress.Builder(
                    RecognitionProgress.PROGRESS_RECOGNIZING)
                    .setDecibel(volume)
                    .build());
        }

        @Override
        public void onTimeout() {
            if (mSingleMode) {
                Log.e(TAG, "google recognize timeout");
                stopRecognizing();
                rejectRecognizing(new RecognitionException(RecognitionException.CODE_TIMEOUT));
            }
        }
    };

    @Override
    protected synchronized void startRecognizing(RecognitionOption recognitionOption) {
        Log.i(TAG, "startRecognizing " + recognitionOption.toString());
        if (mRelease) {
            return;
        }

        mIsRecognizing = true;
        mSingleMode = RecognitionOption.MODE_SINGLE == recognitionOption.getMode();

        // TODO when Rosa_Version >= 2.6.0, RecognitionOption contains timeout option
        long timeoutMs = recognitionOption.getTimeoutMillis();
        if (timeoutMs <= MIN_SINGLE_SPEECH_LENGTH_MS || timeoutMs > MAX_SINGLE_SPEECH_LENGTH_MS) {
            timeoutMs = DEF_SINGLE_SPEECH_LENGTH_MS;
        }

        // Continuous recognition has no timeout concept
        mGoogleAsr.startRecognizing(mSingleMode ? timeoutMs : -1);

        reportRecognizingProgress(new RecognitionProgress.Builder(
                RecognitionProgress.PROGRESS_BEGAN)
                .build());
    }

    @Override
    protected synchronized void stopRecognizing() {
        Log.i(TAG, "stopRecognizing");
        mIsRecognizing = false;
        mGoogleAsr.stopRecognizing();

        reportRecognizingProgress(new RecognitionProgress.Builder(
                RecognitionProgress.PROGRESS_ENDED)
                .build());
    }

    @Override
    public void destroyModule() {
        Log.i(TAG, "destroyModule");
        mRelease = true;
        mGoogleAsr.removeListener(mRecognizeListener);
        mGoogleAsr.release();
    }

}
