package com.ubtrobot.callingsystemservices.locomotion;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ubtrobot.Robot;
import com.ubtrobot.async.ProgressivePromise;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.locomotion.LocomotionException;
import com.ubtrobot.locomotion.LocomotionManager;
import com.ubtrobot.locomotion.LocomotionProgress;

public class LocomotionActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Locomotion";

    private LocomotionManager mLocomotionManager;
    private ProgressivePromise<Void, LocomotionException, LocomotionProgress> mPromise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_locomotion);
        initLoggerView();
        mLocomotionManager = Robot.globalContext().getSystemService(LocomotionManager.SERVICE);
    }

    public void move(View view) {
        addLog(TAG, "Call move: ");
        mPromise = mLocomotionManager.moveStraight(0)
                .progress(progress -> addLog(TAG, "Call move: progress:" + progress.getProgress()))
                .done(aVoid -> addLog(TAG, "Call move: done"))
                .fail(e -> addLog(TAG, "Call move: fail:" + e.getMessage()));
    }

    public void moveBy(View view) {
        addLog(TAG, "Call moveBy: ");
        mPromise = mLocomotionManager.moveStraightBy(0, 5)
                .progress(progress -> addLog(TAG, "Call moveBy: progress：" + progress.getProgress()))
                .done(aVoid -> addLog(TAG, "Call moveBy: done"))
                .fail(e -> addLog(TAG, "Call moveBy: fail:" + e.getMessage()));
    }

    public void turn(View view) {
        addLog(TAG, "turn: ");
        mPromise = mLocomotionManager.turn(30)
                .progress(progress -> addLog(TAG, "Call turn: progress：" + progress.getProgress()))
                .done(aVoid -> addLog(TAG, "Call turn: done"))
                .fail(e -> addLog(TAG, "Call turn: fail:" + e.getMessage()));
    }

    public void turnBy(View view) {
        addLog(TAG, "Call turnBy: ");
        mPromise = mLocomotionManager.turnBy(180, 30)
                .progress(progress -> addLog(TAG, "Call turnBy: progress：" + progress.getProgress()))
                .done(aVoid -> addLog(TAG, "Call turnBy: done"))
                .fail(e -> addLog(TAG, "Call turnBy: fail:" + e.getMessage()));
    }

    public void stop(View view) {
        addLog(TAG, "Call stop: ");
        if (null != mPromise) {
            mPromise.cancel();
        }
    }
}
