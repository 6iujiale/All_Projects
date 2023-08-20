package com.ubtrobot.callingsystemservices.motion;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ubtrobot.Robot;
import com.ubtrobot.async.ProgressivePromise;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.callingsystemservices.databinding.ActivityMotionBinding;
import com.ubtrobot.motion.ActionUris;
import com.ubtrobot.motion.MotionManager;
import com.ubtrobot.motion.PerformingException;
import com.ubtrobot.motion.PerformingProgress;

public class MotionActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Motion";

    private MotionManager mMotionManager;
    private ProgressivePromise<Void, PerformingException, PerformingProgress> mPerforminPromise;

    private Param mParam;

    public class Param {
        public ObservableField<String> posture = new ObservableField<>();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMotionBinding motionBinding = DataBindingUtil.setContentView(this, R.layout.activity_motion);
        mParam = new Param();
        motionBinding.setParams(mParam);
        initLoggerView();
        mMotionManager = Robot.globalContext().getSystemService(MotionManager.SERVICE);
    }

    public void getPosture(View view) {
        String posture = mMotionManager.getPosture().getName();
        addLog(TAG, "getPosture: " + posture);
        mParam.posture.set(posture);
    }

    public void performAction(View view) {
        performAction(ActionUris.HUG);
    }

    private void performAction(Uri uri) {
        if (mPerforminPromise != null) {
            mPerforminPromise.cancel();
        }

        addLog(TAG, "Call performAction: uri:" + uri.toString());
        mPerforminPromise = mMotionManager.performAction(uri)
                .progress((PerformingProgress progress) ->
                        addLog(TAG, "Call performAction: progress: " + progress.getProgress()))
                .done((Void aVoid) -> addLog(TAG, "Call performAction: done."))
                .fail((PerformingException e) -> addLog(TAG, "Call performAction: fail, msg:" + e.getMessage()));
    }

    public void stop(View view) {
        addLog(TAG, "Call stop:");
        if (mPerforminPromise != null) {
            mPerforminPromise.cancel();
        }
    }
}
