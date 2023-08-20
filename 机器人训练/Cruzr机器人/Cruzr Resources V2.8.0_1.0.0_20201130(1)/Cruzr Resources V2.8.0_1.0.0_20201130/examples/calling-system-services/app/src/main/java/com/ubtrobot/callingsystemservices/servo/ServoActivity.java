package com.ubtrobot.callingsystemservices.servo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ubtrobot.Robot;
import com.ubtrobot.async.ProgressivePromise;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.servo.RotationProgress;
import com.ubtrobot.servo.ServoDevice;
import com.ubtrobot.servo.ServoException;
import com.ubtrobot.servo.ServoManager;

import java.util.ArrayList;
import java.util.List;

public class ServoActivity extends BaseLogActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Manager-Servo";

    private ServoManager mServoManager;
    private ProgressivePromise<Void, ServoException, RotationProgress> mPromise;

    private List<String> mSpinnerData = new ArrayList<>();
    private ArrayAdapter<String> mSpinnerAdapter;
    private Spinner mSpinner;

    private String mServoID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_servo);
        initLoggerView();
        mServoManager = Robot.globalContext().getSystemService(ServoManager.SERVICE);

        initSpinner();
        mHandler.post(() -> {
            addLog(TAG,"Querying servo info ...");
            updateServoList();

            if (mSpinnerData.size() > 0) {
                mServoID = mSpinnerData.get(0);
                runOnUiThread(() -> mSpinner.setSelection(0));
            }
        });
    }

    private void updateServoList() {
        mSpinnerData.clear();
        List<ServoDevice> deviceList = mServoManager.getDeviceList();
        for (ServoDevice device : deviceList) {
            mSpinnerData.add(device.getId());
        }

        addLog(TAG, "Call updateServoList: " + mSpinnerData.size());
        runOnUiThread(() -> mSpinnerAdapter.notifyDataSetChanged());
    }

    private void initSpinner() {
        mSpinner = findViewById(R.id.spinner);
        mSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSpinnerData);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    public void rotateBy(View view) {
        if (TextUtils.isEmpty(mServoID)) {
            addLog(TAG, "Call rotateBy: error: SensorIDis empty");
            return;
        }

        addLog(TAG, "Call rotateBy:" + mServoID);
        mPromise = mServoManager.rotateBy(mServoID, 30)
                .done((Void aVoid) -> addLog(TAG, "Call rotateBy: done."))
                .fail((ServoException e) -> addLog(TAG, "Call rotateBy: fail:" + e.getMessage()));
    }

    public void stop(View view) {
        addLog(TAG, "Call stop:");
        if (mPromise != null) {
            mPromise.cancel();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        addLog(TAG, "Selected:" + mSpinnerData.get(position));
        mServoID = mSpinnerData.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mServoID = "";
    }
}
