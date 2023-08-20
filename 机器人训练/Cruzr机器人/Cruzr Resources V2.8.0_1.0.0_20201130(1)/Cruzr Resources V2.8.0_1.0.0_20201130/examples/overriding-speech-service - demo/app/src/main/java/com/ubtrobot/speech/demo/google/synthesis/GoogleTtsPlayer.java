package com.ubtrobot.speech.demo.google.synthesis;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.Log;

import com.google.protobuf.ByteString;
import com.ubtrobot.speech.SynthesisProgress;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class GoogleTtsPlayer {
    private static final String TAG = "GoogleTtsPlayer";

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;

    private volatile boolean mStopped;
    private CountDownTimer mCountDownTimer;
    private GoogleTtsPlayerListener mPlayerListener;

    public GoogleTtsPlayer(GoogleTtsPlayerListener listener) {
        this.mPlayerListener = listener;
        mMediaPlayer = new MediaPlayer();

    }

    public void play(ByteString audioContents) {
        File tempFile = byteStringToFile(audioContents.toByteArray());
        if (null == tempFile || !tempFile.exists() || tempFile.isDirectory()) {
            onFail("Create mp3 temp file error.");
            return;
        }

        mStopped = false;
        playFile(tempFile);
    }

    public void stop() {
        mStopped = true;
        cancelCountDown();

        if (null != mMediaPlayer && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    public boolean isPlaying() {
        return !mStopped;
    }

    public void release() {
        if (!mStopped) {
            stop();
        }

        if (null != mMediaPlayer) {
            mMediaPlayer.release();
        }
    }

    private void playFile(File tempFile) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setLooping(false);
            mMediaPlayer.setDataSource(tempFile.getPath());

            if (mOnPreparedListener == null) {
                mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        if (mStopped) {
                            onFail("MediaPlayer state error.");
                            return;
                        }
                        mediaPlayer.start();
                        int totalDuration = mediaPlayer.getDuration();
                        onProgress(SynthesisProgress.PROGRESS_BEGAN, 0.0f, totalDuration);
                        resetCountDownTask(totalDuration);
                    }
                };
            }

            if (mOnCompletionListener == null) {
                mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.reset();
                        if (null != tempFile && tempFile.exists()) {
                            tempFile.delete();
                        }
                        onProgress(SynthesisProgress.PROGRESS_ENDED, 1.0f, 0);
                        onDone();
                    }
                };
            }

            if (mOnErrorListener == null) {
                mOnErrorListener = new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.e(TAG, "MediaPlayer onError");
                        onFail("MediaPlayer onError:" + what);
                        return true;
                    }
                };
            }

            mMediaPlayer.setOnErrorListener(mOnErrorListener);
            mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            mMediaPlayer.setOnPreparedListener(mOnPreparedListener);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            Log.e(TAG, "MediaPlayer play error", e);
            onFail("MediaPlayer play error");
        }
    }

    private void resetCountDownTask(int duration) {
        Log.d(TAG, "resetCountDownTask: " + duration);
        cancelCountDown();
        if (duration <= 1000) {
            return;
        }

        mCountDownTimer = new CountDownTimer(duration - 300, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!mStopped) {
                    calculateDuration(duration, mMediaPlayer.getCurrentPosition());
                }
            }

            @Override
            public void onFinish() {
                // Nothing
            }
        };

        mCountDownTimer.start();
    }

    private void calculateDuration(int duration, int currentPosition) {
        if (currentPosition <= 0 || currentPosition >= duration) {
            return;
        }

        float progress = (float) currentPosition / duration;
        onProgress(SynthesisProgress.PROGRESS_PLAYING, progress, duration - currentPosition);
    }

    private void cancelCountDown() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    private File byteStringToFile(byte[] bytes) {
        File tempFile = null;
        FileOutputStream outputStream = null;
        try {
            tempFile = File.createTempFile(UUID.randomUUID().toString(), ".mp3");
            if (tempFile != null) {
                outputStream = new FileOutputStream(tempFile);
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(outputStream);
        }

        return tempFile;
    }

    private void onDone() {
        if (null != mPlayerListener) {
            mPlayerListener.onCompleted();
        }
    }

    private void onProgress(String progressState, float progress, int remainTime) {
        if (null != mPlayerListener) {
            mPlayerListener.onProgress(progressState, progress, remainTime);
        }
    }

    private void onFail(String message) {
        if (null != mPlayerListener) {
            mPlayerListener.onError(message);
        }
    }

    private void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface GoogleTtsPlayerListener {
        void onProgress(String progressState, float progress, int remainTime);

        void onCompleted();

        void onError(String message);
    }
}
