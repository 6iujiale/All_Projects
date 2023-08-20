package com.ubtrobot.callingsystemservices.speech;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.ubtrobot.Robot;
import com.ubtrobot.async.ProgressivePromise;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.callingsystemservices.databinding.ActivitySpeechBinding;
import com.ubtrobot.callingsystemservices.utils.FooUtils;
import com.ubtrobot.rosa.Build;
import com.ubtrobot.speech.RecognitionException;
import com.ubtrobot.speech.RecognitionOption;
import com.ubtrobot.speech.RecognitionProgress;
import com.ubtrobot.speech.RecognitionResult;
import com.ubtrobot.speech.SpeakingVoice;
import com.ubtrobot.speech.SpeechManager;
import com.ubtrobot.speech.SynthesisException;
import com.ubtrobot.speech.SynthesisOption;
import com.ubtrobot.speech.SynthesisProgress;

import java.util.ArrayList;

public class SpeechActivity extends BaseLogActivity implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "Manager-Speech";

    private SpeechManager mSpeechManager;
    private SeekBar sbSpeed;
    private SeekBar sbVolume;
    private Spinner mSpinnerSpeaker;

    private String mSpeakerId = "";
    private int mSpeakVolume = 50;//1~100
    private float mSpeakerSpeed = 1.0f;//0.1~1~10 倍速模式

    private ProgressivePromise<Void, SynthesisException, SynthesisProgress> mSynthesisPromise;
    private ProgressivePromise<RecognitionResult, RecognitionException, RecognitionProgress> mRecognitionPromise;

    private ArrayAdapter mSpinnerAdapter;
    private ArrayList<String> mVoiceNameList = new ArrayList<>();

    public class Param {
        public ObservableField<String> timeoutTxt = new ObservableField<>();
        public ObservableField<String> nlpTxt = new ObservableField<>();
        public ObservableField<String> ttsTxt = new ObservableField<>();

        public ObservableField<String> speed = new ObservableField<>();
        public ObservableField<String> volume = new ObservableField<>();
    }

    private Param mParam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySpeechBinding speechBinding = DataBindingUtil.setContentView(this, R.layout.activity_speech);
        mParam = new Param();
        speechBinding.setParams(mParam);

        initLoggerView();
        mSpeechManager = Robot.globalContext().getSystemService(SpeechManager.SERVICE);
        initView();
        mHandler.post(() -> {
            addLog(TAG, "Querying speaker info ...");
            updateSpeakerList();
        });
    }

    public void startRecognizing(View view) {
        startRecognizing(true);
    }

    public void startRecognizing2(View view) {
        startRecognizing(false);
    }

    private void startRecognizing(boolean isSingle) {
        if (null != mRecognitionPromise) {
            mRecognitionPromise.cancel();
        }
        int mode = isSingle ? RecognitionOption.MODE_SINGLE : RecognitionOption.MODE_CONTINUOUS;
        long timeout = FooUtils.string2Long(mParam.timeoutTxt.get(), 12) * 1000;
        RecognitionOption.Builder builder = new RecognitionOption.Builder(mode);

        if (Build.VERSION.equals("2.7.0")) {
            builder.setTimeoutMillis(timeout);// Only version> = 2.7.0 supports timeout parameter
        }

        addLog(TAG, "startRecognizing: isSingle:" + isSingle);
        mRecognitionPromise = mSpeechManager.recognize(builder.build())
                .done(result -> addLog(TAG, "startRecognizing: done:" + result.getText()))
                .progress(progress -> {
                    if (progress.inProgress(RecognitionProgress.PROGRESS_RECOGNITION_TEXT_RESULT)) {
                        addLog(TAG, "startRecognizing: result:" + progress.getTextResult());
                    } else if (progress.inProgress(RecognitionProgress.PROGRESS_RECOGNIZING)) {
                        addLog(TAG, "startRecognizing: volume:" + progress.getDecibel());
                    }
                })
                .fail(e -> addLog(TAG, "startRecognizing: fail:" + e.getMessage()));
    }

    public void stopRecognizing(View view) {
        addLog(TAG, "stopRecognizing: ");
        if (null != mRecognitionPromise) {
            mRecognitionPromise.cancel();
        }
    }

    public void startUnderstanding(View view) {
        String nlpText = mParam.nlpTxt.get();
        if (TextUtils.isEmpty(nlpText)) {
            addLog(TAG, "startUnderstanding: error: NLP input text is empty.");
            return;
        }

        addLog(TAG, "startUnderstanding: " + nlpText);
        mSpeechManager.understand(nlpText)
                .done(result -> addLog(TAG, "startUnderstanding: done:" + result.getSpeechFulfillment().getText()))
                .fail(e -> addLog(TAG, "startUnderstanding: fail:" + e.getMessage()));
    }

    public void startSynthesizing(View view) {
        if (null != mSynthesisPromise) {
            mSynthesisPromise.cancel();
        }

        String ttsText = mParam.ttsTxt.get();
        if (TextUtils.isEmpty(ttsText)) {
            addLog(TAG, "startSynthesizing: error: TTS input text is empty.");
            return;
        }

        SynthesisOption synthesisOption = new SynthesisOption.Builder(ttsText)
                .setSpeakingVoiceId(mSpeakerId)//发音人
                .setSpeakingSpeed(mSpeakerSpeed)//语速 0.1~1~10
                .setSpeakingVolume(mSpeakVolume)//音量 0~100
                .build();

        addLog(TAG, "startSynthesizing: " + ttsText);
        mSynthesisPromise = mSpeechManager.synthesize(synthesisOption)
                .progress(progress -> {
                    String progressLog = "" + progress.getProgress() + " " + progress.getPlayProgress() + " " + progress.getRemainingTimeMillis();
                    addLog(TAG, "startSynthesizing:" + progressLog);
                })
                .done(aVoid -> addLog(TAG, "startSynthesizing: done"))
                .fail(e -> addLog(TAG, "startSynthesizing: fail:" + e.getMessage()));
    }

    public void stopSynthesizing(View view) {
        addLog(TAG, "stopSynthesizing: ");
        if (null != mSynthesisPromise) {
            mSynthesisPromise.cancel();
        }
    }

    private void updateSpeakerList() {
        mVoiceNameList.clear();
        for (SpeakingVoice speaker : mSpeechManager.getSpeakingVoiceList()) {
            mVoiceNameList.add(speaker.getName());
        }

        addLog(TAG, "updateSpeakerList:" + mVoiceNameList.size());
        if (mVoiceNameList.size() > 0) {
            mSpeakerId = mVoiceNameList.get(0);
            runOnUiThread(() -> mSpinnerSpeaker.setSelection(0));

        }
        runOnUiThread(() -> mSpinnerAdapter.notifyDataSetChanged());
    }

    private void initView() {
        mSpinnerSpeaker = findViewById(R.id.sp_voiceId);
        mSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mVoiceNameList);
        mSpinnerSpeaker.setAdapter(mSpinnerAdapter);
        mSpinnerSpeaker.setOnItemSelectedListener(this);

        int defaultSpeedProgress = 10;
        sbSpeed = findViewById(R.id.sb_speed);
        setSpeakSpeed(defaultSpeedProgress);
        sbSpeed.setProgress(defaultSpeedProgress);
        sbSpeed.setOnSeekBarChangeListener(this);

        sbVolume = findViewById(R.id.sb_volume);
        setSpeakVolume(mSpeakVolume);
        sbVolume.setProgress(mSpeakVolume);
        sbVolume.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long count) {
        addLog(TAG, "Selected:" + mVoiceNameList.get(position));
        mSpeakerId = mVoiceNameList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //nothing
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb_speed:
                setSpeakSpeed(progress);
                break;
            case R.id.sb_volume:
                setSpeakVolume(progress);
                break;
            default:
                // nothing
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // nothing
    }

    private void setSpeakVolume(int volume) {
        mSpeakVolume = volume;
        mParam.volume.set(mSpeakVolume + "");
        addLog(TAG, "SpeakVolume >> " + mSpeakVolume);
    }

    private void setSpeakSpeed(int speed) {
        if (speed <= 10) {//1~10
            mSpeakerSpeed = (float) speed / 10;
        } else {//11~19
            mSpeakerSpeed = 1.0f + speed % 10;
        }

        mParam.speed.set(mSpeakerSpeed + "");
        addLog(TAG, "SpeakSpeed >> " + mSpeakerSpeed);
    }
}
