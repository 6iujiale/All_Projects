package com.ubtrobot.callingsystemservices.power;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ubtrobot.Robot;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.power.PowerException;
import com.ubtrobot.power.PowerManager;

public class PowerActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Power";

    private PowerManager mPowerManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_power);
        initLoggerView();
        mPowerManager = Robot.globalContext().getSystemService(PowerManager.SERVICE);
    }

    public void wakeUp(View view) {
        addLog(TAG, "Call wakeUp: ");
        mPowerManager.wakeUp()
                .done((Void aVoid) -> addLog(TAG, "Call wakeUp: done."))
                .fail((PowerException e) -> addLog(TAG, "Call wakeUp: fail. msg:" + e.getMessage()));
    }

    public void sleep(View view) {
        addLog(TAG, "Call sleep: ");
        mPowerManager.sleep()
                .done((Void aVoid) -> addLog(TAG, "Call sleep: done."))
                .fail((PowerException e) -> addLog(TAG, "Call sleep: fail. msg:" + e.getMessage()));
    }

    public void shutdown(View view) {
        addLog(TAG, "Call shutdown: ");
        mPowerManager.shutdown()
                .done(aVoid -> addLog(TAG, "Call shutdown: done"))
                .fail(e -> addLog(TAG, "Call shutdown: fail:" + e.getMessage()));
    }
}
