package com.ubtrobot.cruzr.visual;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.google.gson.Gson;
import com.ubtechinc.cruzr.visual.Result;
import com.ubtechinc.cruzr.visual.VisualListener;
import com.ubtechinc.cruzr.visual.VisualManager;
import com.ubtechinc.cruzr.visual.VisualParam;
import com.ubtrobot.callingsystemservices.R;

import java.util.UUID;

public class VisualActivity extends AppCompatActivity {

    private static final String TAG = "Visual-MainActivity";

    private RadioGroup mTypeRadioGroup;
    private CheckBox mOnlineCheckBox;
    private CheckBox mShowUICheckBox;
    private EditText mTimeOutEdit;
    private EditText mImageFileEdit;
    private EditText mImagePathEdit;
    private TextView mResultText;

    private VisualManager mVisualManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_visaul);
        mTypeRadioGroup = findViewById(R.id.type_radio_group);
        mOnlineCheckBox = findViewById(R.id.on_line_check_box);
        mShowUICheckBox = findViewById(R.id.show_ui_check_box);
        mTimeOutEdit = findViewById(R.id.time_out_edit);
        mImageFileEdit = findViewById(R.id.image_file_edit);
        mImagePathEdit = findViewById(R.id.image_path_edit);
        mResultText = findViewById(R.id.result_text);
    }

    private void initData() {
        VisualManager.getInstance().init(getApplicationContext());
        mVisualManager = VisualManager.getInstance();
        mVisualManager.addListener(new MainListener());
    }


    public void onBackClick(View view) {
        onBackPressed();
    }

    public void onStartClick(View view) {
        Log.i(TAG, "onStartClick");
        mResultText.setText("Waiting");

        String requestId = UUID.randomUUID().toString();
        int requestType = getTypeFromRadioGroup();
        boolean online = mOnlineCheckBox.isChecked();
        boolean showUi = mShowUICheckBox.isChecked();
        int timeOut = Integer.valueOf(mTimeOutEdit.getText().toString()) * 1000;
        String imageFile = mImageFileEdit.getText().toString();
        String imagePath = mImagePathEdit.getText().toString();

        //  设置参数
        VisualParam param = new VisualParam.Builder()
                .requestId(requestId)     // 设置请求ID
                .requestType(requestType) // 设置请求类型
                .online(online)           // 是否在线
                .showUi(showUi)           // 是否显示界面
                .timeOut(timeOut)         // 设置超时时间
                .imageFile(imageFile)     // 设置需要修改的图片路径
                .imagePath(imagePath)     // 设置图片保存的目录
                .groupId("ce2f5dca354140fc82fe444e38d82f62") // UBTECHTEST
                .build();

        mVisualManager.start(param);
    }

    // 人脸检测，人脸追踪
    private void faceDetection(String imageFile) {
        VisualParam param = new VisualParam.Builder()
                .requestType(VisualParam.TYPE_DETECTION)
                .build();
        mVisualManager.start(param);
    }

    // 离线图片识别
    private void imageRecognizeLocal(String imageFile) {
        VisualParam param = new VisualParam.Builder()
                .requestType(VisualParam.TYPE_IMAGE_BG)
                .imageFile(imageFile)   // 传入图片路径
                .online(false)          // 离线
                .build();
        mVisualManager.start(param);
    }

    // 在线图片识别
    private void imageRecognizeOnline(String imageFile, String groupId) {
        VisualParam param = new VisualParam.Builder()
                .requestType(VisualParam.TYPE_IMAGE_BG)
                .imageFile(imageFile)  // 传入图片路径
                .online(true)          // 在线
                .groupId(groupId)      // groupId
                .build();
        mVisualManager.start(param);
    }

    // 人脸拍照
    private void takeFacePhoto(boolean showUi, String imagePath) {
        VisualParam param = new VisualParam.Builder()
                .requestType(VisualParam.TYPE_TAKE_PHOTO)
                .showUi(showUi)           // 是否显示界面
                .imagePath(imagePath)     // 设置图片保存的目录
                .build();
        mVisualManager.start(param);
    }

    private int getTypeFromRadioGroup() {
        int resId = mTypeRadioGroup.getCheckedRadioButtonId();
        switch (resId) {
            case R.id.recognition_radio:
                return VisualParam.TYPE_RECOGNITION;

            case R.id.detection_radio:
                return VisualParam.TYPE_DETECTION;

            case R.id.image_radio:
                return VisualParam.TYPE_IMAGE;

            case R.id.image_bg_radio:
                return VisualParam.TYPE_IMAGE_BG;

            case R.id.take_photo_radio:
                return VisualParam.TYPE_TAKE_PHOTO;

            default:
                return VisualParam.TYPE_DEFAULT;
        }
    }

    public void onStopClick(View view) {
        mVisualManager.stop();  // 停止视觉
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVisualManager.onDestroy();
    }

    public class MainListener extends VisualListener {
        @Override
        public void onDone(String message) {
            // 根据业务需求解析json
            Log.d(TAG, "onDone : " + message);

            try {
                Result result = new Gson().fromJson(message, Result.class);
                Log.i(TAG, "result : " + result);
            } catch (Exception e) {
                Log.e(TAG, "fromJson Exception", e);
            }

            runOnUiThread(() -> {
                if (mResultText != null) {
                    mResultText.setText("onDone : " + message);
                }
            });
        }

        @Override
        public void onFail(int code, String error) {
            // code 为错误码, error 为错误信息
            Log.d(TAG, "onFail code : " + code + ", error : " + error);
            runOnUiThread(() -> {
                if (mResultText != null) {
                    mResultText.setText("onFail code : " + code + ", error : " + error);
                }
            });
        }

        @Override
        public void onProgress(String message) {
            Log.d(TAG, "onProgress : " + message);
            runOnUiThread(() -> {
                if (mResultText != null) {
                    mResultText.setText("onProgress : " + message);
                }
            });
        }
    }
}
