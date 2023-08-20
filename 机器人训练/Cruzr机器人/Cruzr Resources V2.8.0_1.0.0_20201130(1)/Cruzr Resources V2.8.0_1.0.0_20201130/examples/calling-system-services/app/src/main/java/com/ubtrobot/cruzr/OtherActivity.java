package com.ubtrobot.cruzr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.cruzr.leisure.LeisureActivity;
import com.ubtrobot.cruzr.settings.SettingsActivity;
import com.ubtrobot.cruzr.user.UserActivity;
import com.ubtrobot.cruzr.media.MediaActivity;
import com.ubtrobot.cruzr.visual.VisualActivity;
import com.ubtrobot.cruzr.voiceass.VoiceAssistantActivity;

public class OtherActivity extends AppCompatActivity {
    private static final String TAG = "OtherActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.leisure:
                Log.i(TAG, "Click leisure.");
                startActivity(new Intent(this, LeisureActivity.class));
            break;

            case R.id.media:
                Log.i(TAG, "Click media.");
                startActivity(new Intent(this, MediaActivity.class));
                break;

            case R.id.voiceAss:
                Log.i(TAG, "Click voiceAss.");
                startActivity(new Intent(this, VoiceAssistantActivity.class));
                break;

            case R.id.user:
                Log.i(TAG, "Click user.");
                startActivity(new Intent(this, UserActivity.class));
                break;

            case R.id.visual:
                Log.i(TAG, "Click visual.");
                startActivity(new Intent(this, VisualActivity.class));
                break;
            case R.id.settings:
                Log.i(TAG, "Click settings.");
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
    }
}
