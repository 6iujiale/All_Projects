package com.ubtrobot.callingsystemservices.emotion;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ubtrobot.Robot;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.callingsystemservices.databinding.ActivityEmotionBinding;
import com.ubtrobot.emotion.EmotionException;
import com.ubtrobot.emotion.EmotionManager;
import com.ubtrobot.resource.Resource;
import com.ubtrobot.resource.ResourceException;
import com.ubtrobot.resource.ResourceManager;
import com.ubtrobot.resource.ResourceTypes;
import com.ubtrobot.resource.emotion.EmotionProperties;

import java.util.ArrayList;
import java.util.List;

public class EmotionActivity extends BaseLogActivity {

    private static final String TAG = "Manager-Emotion";

    private ActivityEmotionBinding mBinding;

    private List<Uri> mEmotionUriList;
    private List<String> mEmotionNameList;
    private int mSelectedPosition;

    private ResourceManager mResourceManager;
    private EmotionManager mEmotionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_emotion);
        initLoggerView();
        mEmotionUriList = new ArrayList<>();
        mEmotionNameList = new ArrayList<>();

        mResourceManager = Robot.globalContext().getSystemService(ResourceManager.SERVICE);
        mEmotionManager = Robot.globalContext().getSystemService(EmotionManager.SERVICE);

        mBinding.emotionList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedPosition = position;
                addLog(TAG, "Selected position:" + mSelectedPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getEmotionList(View view) {
        addLog(TAG, "Call getEmotionList:");
        mResourceManager.getResourceList(ResourceTypes.EMOTION)
                .done((List<Resource<Parcelable>> resources) -> {
                    mEmotionUriList.clear();
                    mEmotionNameList.clear();

                    for (Resource<Parcelable> resource : resources) {
                        mEmotionUriList.add(resource.getUri());
                        mEmotionNameList.add(
                                ((EmotionProperties) resource.getProperties())
                                        .getName(EmotionActivity.this)
                        );
                    }

                    addLog(TAG, "Call getEmotionList: done:" + mEmotionNameList.size());
                    mBinding.emotionList.setAdapter(
                            new ArrayAdapter<>(this,
                                    android.R.layout.simple_spinner_item, mEmotionNameList
                            ));

                })
                .fail((ResourceException e) -> {
                    addLog(TAG, "Call getEmotionList: fail:" + e.getMessage());
                });
    }

    public void expressEmotion(View view) {
        if (mEmotionUriList.isEmpty()) {
            addLog(TAG, "Please click get emotion list.");
            return;
        }

        addLog(TAG, "Call expressEmotion: url = " + mEmotionUriList.get(mSelectedPosition));
        mEmotionManager.express(mEmotionUriList.get(mSelectedPosition))
                .done((Void aVoid) -> {
                    addLog(TAG, "Call expressEmotion: done.");
                })
                .fail((EmotionException e) -> {
                    addLog(TAG, "Call expressEmotion: fail:" + e.getMessage());
                });
    }

    public void dismissEmotion(View view) {
        addLog(TAG, "Call dismissEmotion done.");
        mEmotionManager.dismiss()
                .done((Void aVoid) -> {
                    addLog(TAG, "Call dismissEmotion: done.");
                })
                .fail((EmotionException e) -> {
                    addLog(TAG, "Call dismissEmotion: fail:" + e.getMessage());
                });
    }

}
