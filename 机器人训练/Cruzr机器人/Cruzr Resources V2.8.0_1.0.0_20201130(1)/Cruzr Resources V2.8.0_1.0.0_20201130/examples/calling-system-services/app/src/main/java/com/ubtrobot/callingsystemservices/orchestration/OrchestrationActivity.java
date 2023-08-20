package com.ubtrobot.callingsystemservices.orchestration;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ubtrobot.Robot;
import com.ubtrobot.async.ProgressivePromise;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.orchestration.OrchestrationManager;
import com.ubtrobot.orchestration.OrchestrationUris;
import com.ubtrobot.orchestration.PlayException;
import com.ubtrobot.orchestration.PlayProgress;

public class OrchestrationActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Orchestration";

    private OrchestrationManager mOrchestrationManager;
    private ProgressivePromise<Void, PlayException, PlayProgress> mPromise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_orchestration);
        initLoggerView();
        mOrchestrationManager = Robot.globalContext().getSystemService(OrchestrationManager.SERVICE);
    }

    public void play(View view) {
        Uri uri = OrchestrationUris.BBOOM_BBOOM;
        addLog(TAG, "Call play:" + uri.toString());
        mPromise = mOrchestrationManager.play(uri)
                .done((Void aVoid) -> addLog(TAG, "Call play: done."))
                .fail((PlayException e) -> addLog(TAG, "Call play: fail:" + e.getMessage()));
    }

    public void stop(View view) {
        addLog(TAG, "Call stop:");
        if (mPromise != null) {
            mPromise.cancel();
        }
    }
}
