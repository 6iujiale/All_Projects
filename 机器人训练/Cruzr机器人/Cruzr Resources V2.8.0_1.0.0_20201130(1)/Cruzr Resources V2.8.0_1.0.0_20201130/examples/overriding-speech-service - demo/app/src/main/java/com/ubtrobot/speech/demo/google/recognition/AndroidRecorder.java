package com.ubtrobot.speech.demo.google.recognition;

import android.media.AudioRecord;
import android.os.Process;
import android.util.Log;
import android.util.Pair;

import com.ubtrobot.speech.AudioRecordListener;
import com.ubtrobot.speech.AudioRecordStateListener;
import com.ubtrobot.speech.AudioRecorder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Executor;

public class AndroidRecorder implements AudioRecorder {
    private static final String TAG = "AndroidRecorder";

    private volatile static AndroidRecorder mInst = null;

    private final LinkedList<Pair<Executor, AudioRecordListener>> mRecordListeners = new LinkedList<>();
    private final LinkedList<AudioRecordStateListener> mStateListener = new LinkedList<>();

    private AudioRecord mRecorder = null;
    private volatile int mState;
    private byte[] mBuf;

    private int mInbytes;
    private Thread mThread;
    private RecorderRunnable mRunnable;

    private AndroidRecorder() {
    }

    public static AndroidRecorder getInstance() {
        if (mInst == null) {
            synchronized (AndroidRecorder.class) {
                if (mInst == null) {
                    mInst = new AndroidRecorder();
                    mInst.mState = STATE_IDLE;
                }
            }
        }

        return mInst;
    }

    public synchronized int initRecorder(int sampleRateInHz, int channelConfig, int audioFormat, int audioSource) {
        int bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
        mInbytes = bufferSizeInBytes;
        if (-2 == bufferSizeInBytes) {
            Log.i(TAG, "buffer Size exception:");
            return -2;
        } else if (mRecorder != null) {
            Log.i(TAG, "recorder != null,no need initRecorder,return");
            return -1;
        } else {
            mBuf = new byte[bufferSizeInBytes];
            mRecorder = new AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes);
            return 0;
        }
    }

    private class RecorderRunnable implements Runnable {
        private volatile boolean mRun;

        RecorderRunnable() {
            mRun = true;
        }

        public void run() {
            while (mRun) {
                int realSize = mRecorder.read(mBuf, 0, mInbytes);
                notifyRecordListener(mBuf, realSize);
            }

            Log.i(TAG, "finish " + toString());
        }

        void stopSelf() {
            mRun = false;
        }
    }

    private void notifyRecordListener(final byte[] data, final int length) {
        synchronized (mRecordListeners) {
            for (final Pair<Executor, AudioRecordListener> listenerPair : mRecordListeners) {
                listenerPair.first.execute(new Runnable() {
                    @Override
                    public void run() {
                        listenerPair.second.onRecord(data, length);
                    }
                });
            }
        }
    }

    private void notifyStateListener(int state) {
        synchronized (mStateListener) {
            for (AudioRecordStateListener listener : mStateListener) {
                listener.onStateChanged(state, null);
            }
        }
    }

    @Override
    public int getState() {
        return mState;
    }

    @Override
    public void registerStateListener(AudioRecordStateListener listener) {
        synchronized (mStateListener) {
            mStateListener.add(listener);
        }
    }

    @Override
    public void unregisterStateListener(AudioRecordStateListener listener) {
        synchronized (mStateListener) {
            mStateListener.remove(listener);
        }
    }

    @Override
    public void registerRecordListener(AudioRecordListener listener, Executor executor, String filter) {
        synchronized (mRecordListeners) {
            mRecordListeners.add(new Pair<>(executor, listener));
        }
    }

    @Override
    public void unregisterRecordListener(AudioRecordListener listener) {
        synchronized (mRecordListeners) {
            Iterator<Pair<Executor, AudioRecordListener>> listIterator
                    = mRecordListeners.iterator();
            while (listIterator.hasNext()) {
                Pair<Executor, AudioRecordListener> pair = listIterator.next();
                if (pair.second == listener) {
                    listIterator.remove();
                }
            }
        }
    }

    @Override
    public synchronized boolean start() {
        if (STATE_RECORDING != mState) {
            mState = STATE_RECORDING;
            mRecorder.startRecording();
            Log.i(TAG, "create new thread:" + Process.myTid());
            mRunnable = new RecorderRunnable();
            mThread = new Thread(mRunnable);
            mThread.start();

            notifyStateListener(mState);
            return true;
        } else {
            Thread.State state = mThread.getState();
            if (state != Thread.State.RUNNABLE) {
                Log.i(TAG, "recorder running but not really running:" + state);
            }
            return false;
        }
    }

    @Override
    public synchronized boolean stop() {
        if (null != mRunnable) {
            mRunnable.stopSelf();
        }

        if (null != mRecorder) {
            mRecorder.stop();
        }

        if (null != mThread) {
            mThread.interrupt();
        }

        if (mState == STATE_IDLE) {
            return false;
        }

        mRunnable = null;
        mState = STATE_IDLE;
        notifyStateListener(mState);
        return true;
    }

    public synchronized void release() {
        if (mState == STATE_RECORDING) {
            stop();
        }

        if (null != mRecorder) {
            mRecorder.release();
            mRecorder = null;
        }
    }

    @Override
    public boolean enableBeamforming() {
        return false;
    }

    @Override
    public boolean disableBeamforming() {
        return false;
    }

    @Override
    public boolean isBeamformingEnabled() {
        return false;
    }

}
