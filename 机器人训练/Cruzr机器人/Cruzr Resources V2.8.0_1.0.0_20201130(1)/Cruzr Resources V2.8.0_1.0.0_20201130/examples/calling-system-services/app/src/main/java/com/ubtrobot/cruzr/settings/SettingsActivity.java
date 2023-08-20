package com.ubtrobot.cruzr.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ubtechinc.lib.SettingValueFetcher;
import com.ubtrobot.callingsystemservices.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.set_value:
                //close chassis motion
                boolean insertSuccess = SettingValueFetcher.setStringValue(this, "cruiser_chassis_motion_state", "false");
                Log.i("settings", "setStringValue ret = " + insertSuccess);
                break;
            case R.id.get_value:
                // get chassis motion state
                String value = SettingValueFetcher.getStringValue(this, "cruiser_chassis_motion_state","true");
                Log.i("settings", "getStringValue ret = " + value);
                break;
        }
    }
}
