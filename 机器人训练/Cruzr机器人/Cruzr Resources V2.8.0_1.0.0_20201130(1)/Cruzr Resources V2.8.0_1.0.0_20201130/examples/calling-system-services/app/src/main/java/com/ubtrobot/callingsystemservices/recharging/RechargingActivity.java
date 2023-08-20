package com.ubtrobot.callingsystemservices.recharging;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ubtrobot.Robot;
import com.ubtrobot.async.ProgressivePromise;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.recharging.ConnectingProgress;
import com.ubtrobot.recharging.DisconnectingProgress;
import com.ubtrobot.recharging.RechargingException;
import com.ubtrobot.recharging.RechargingManager;

public class RechargingActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Recharging";

    private RechargingManager mRecharging;
    private ProgressivePromise<Void, RechargingException, ConnectingProgress> connectPromise;
    private ProgressivePromise<Void, RechargingException, DisconnectingProgress> disconnectPromise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_recharging);
        initLoggerView();
        mRecharging = Robot.globalContext().getSystemService(RechargingManager.SERVICE);
    }

    public void connectToStation(View view) {
        addLog(TAG, "Call connectToStation:");
        connectPromise = mRecharging.connectToStation().progress((ConnectingProgress progress) ->
                addLog(TAG, "Call connectToStation: progress:" + progress.toString()))
                .done((Void aVoid) -> addLog(TAG, "Call connectToStation: done."))
                .fail((RechargingException e) -> addLog(TAG, "Call connectToStation: fail:" + e.getMessage()))
                .always((i, aVoid, e) -> connectPromise = null);
    }

    public void disconnectFromStation(View view) {
        addLog(TAG, "Call disconnectFromStation:");
        disconnectPromise = mRecharging.disconnectFromStation()
                .progress(progress -> addLog(TAG, "disconnectFromStation: progress:" + progress.getProgress()))
                .done(aVoid -> addLog(TAG, "disconnectFromStation: done"))
                .fail(e -> addLog(TAG, "disconnectFromStation: fail:" + e.getMessage()))
                .always((i, aVoid, e) -> disconnectPromise = null);
    }

    public void stop(View view) {
        addLog(TAG, "Call stop:");
        if (null != connectPromise) {
            connectPromise.cancel();
        }
        if (null != disconnectPromise) {
            disconnectPromise.cancel();
        }
    }
}
