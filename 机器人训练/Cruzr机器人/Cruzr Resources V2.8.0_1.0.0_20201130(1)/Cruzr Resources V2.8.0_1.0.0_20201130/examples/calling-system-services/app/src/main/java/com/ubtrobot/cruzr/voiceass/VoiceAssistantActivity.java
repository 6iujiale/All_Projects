package com.ubtrobot.cruzr.voiceass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.ubtechinc.cruzr.assistant.sdk.AssistantManager;
import com.ubtrobot.callingsystemservices.R;

public class VoiceAssistantActivity extends AppCompatActivity implements View.OnClickListener {
    private View mContentView;
    private Button btnShowAss;
    private Button btnHideAss;
    private Button btnShowSpecificPrompt;
    private Button btnHideSpecificPrompt;
    private Button btnShowPartWakeup;
    private Button btnHidePartWakeup;
    private Button btnShowPartMessage;
    private Button btnHidePartMessage;
    private Button btnTurnOn;
    private Button btnTurnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = LayoutInflater.from(this).inflate(R.layout.activity_voice_ass, null);
        setContentView(mContentView);
        findViews();
    }

    private void findViews() {
        btnShowAss = mContentView.findViewById(R.id.btnShowAss);
        btnHideAss = mContentView.findViewById(R.id.btnHideAss);
        btnShowSpecificPrompt = mContentView.findViewById(R.id.btnShowSpecificPrompt);
        btnHideSpecificPrompt = mContentView.findViewById(R.id.btnHideSpecificPrompt);
        btnShowPartWakeup = mContentView.findViewById(R.id.btnShowPartWakeup);
        btnHidePartWakeup = mContentView.findViewById(R.id.btnHidePartWakeup);
        btnShowPartMessage = mContentView.findViewById(R.id.btnShowPartMessage);
        btnHidePartMessage = mContentView.findViewById(R.id.btnHidePartMessage);
        btnTurnOn = mContentView.findViewById(R.id.btnTurnOn);
        btnTurnOff = mContentView.findViewById(R.id.btnTurnOff);
        btnShowAss.setOnClickListener(this);
        btnHideAss.setOnClickListener(this);
        btnShowSpecificPrompt.setOnClickListener(this);
        btnHideSpecificPrompt.setOnClickListener(this);
        btnShowPartWakeup.setOnClickListener(this);
        btnHidePartWakeup.setOnClickListener(this);
        btnShowPartMessage.setOnClickListener(this);
        btnHidePartMessage.setOnClickListener(this);
        btnTurnOn.setOnClickListener(this);
        btnTurnOff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowAss:
                AssistantManager.get(this).showAssistant();
                break;

            case R.id.btnHideAss:
                AssistantManager.get(this).hideAssistant();
                break;

            case R.id.btnShowSpecificPrompt:
                AssistantManager.get(this).showSpecificPrompt("com.ubtechinc.cruzr.dance");
                break;

            case R.id.btnHideSpecificPrompt:
                AssistantManager.get(this).hideSpecificPrompt("com.ubtechinc.cruzr.dance");
                break;

            case R.id.btnShowPartWakeup:
                AssistantManager.get(this).showOrHidePart(AssistantManager.TYPE_SHOW_PART_WAKEUP);
                break;

            case R.id.btnHidePartWakeup:
                AssistantManager.get(this).showOrHidePart(AssistantManager.TYPE_HIDE_PART_WAKEUP);
                break;

            case R.id.btnShowPartMessage:
                AssistantManager.get(this).showOrHidePart(AssistantManager.TYPE_SHOW_PART_MESSAGE);
                break;

            case R.id.btnHidePartMessage:
                AssistantManager.get(this).showOrHidePart(AssistantManager.TYPE_HIDE_PART_MESSAGE);
                break;

            case R.id.btnTurnOn:
                AssistantManager.get(this).switchAssistant(AssistantManager.TYPE_TURN_ON);
                break;

            case R.id.btnTurnOff:
                AssistantManager.get(this).switchAssistant(AssistantManager.TYPE_TURN_OFF);
                break;
        }
    }
}
