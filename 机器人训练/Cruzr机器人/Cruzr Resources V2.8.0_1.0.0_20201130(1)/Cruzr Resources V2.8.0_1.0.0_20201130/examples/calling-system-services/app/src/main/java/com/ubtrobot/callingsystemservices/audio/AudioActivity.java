package com.ubtrobot.callingsystemservices.audio;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ubtrobot.Robot;
import com.ubtrobot.audio.AudioManager;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;

public class AudioActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Audio";

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_audio);
        initLoggerView();
        mAudioManager = Robot.globalContext().getSystemService(AudioManager.SERVICE);
    }

    public void play(View view) {
        // todo no audio resource
        addLog(TAG, "This function is not supported yet.");
    }

    public void stop(View view) {
        // todo no audio resource
        addLog(TAG, "This function is not supported yet.");
    }
}
