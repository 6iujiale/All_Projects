package com.ubtrobot.callingsystemservices.resource;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

import com.ubtrobot.Robot;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.resource.Resource;
import com.ubtrobot.resource.ResourceException;
import com.ubtrobot.resource.ResourceManager;
import com.ubtrobot.resource.ResourceTypes;
import com.ubtrobot.resource.orchestration.OrchestrationProperties;

import java.util.List;

public class ResourceActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Resource";

    private ResourceManager mResourceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_resource);
        initLoggerView();
        mResourceManager = Robot.globalContext().getSystemService(ResourceManager.SERVICE);
    }

    public void getOrchestrationList(View view) {
        clearLog();
        String resourceType = ResourceTypes.ORCHESTRATION;
        addLog(TAG, "Call getOrchestrationList: resourceType:" + resourceType);
        mResourceManager.getResourceList(resourceType)
                .done((List<Resource<Parcelable>> orchestrationList) -> {
                    addLog(TAG, "Call getOrchestrationList: done:" + orchestrationList.size());
                    int i = 0;
                    for (Resource<Parcelable> resource : orchestrationList) {
                        i++;
                        addLog(TAG, "-------------------------\t" + i + "\t-------------------------");
                        OrchestrationProperties orchestration = (OrchestrationProperties) resource.getProperties();
                        addLog(TAG, "uri:" + resource.getUri());
                        addLog(TAG, "name: " + orchestration.getName(this));
                    }
                    addLog(TAG, "-------------------------\t\t-------------------------");
                })
                .fail((ResourceException e) -> addLog(TAG, "Call getOrchestrationList fail."));
    }
}
