package com.ubtrobot.callingsystemservices.sensor;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ubtrobot.Robot;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.callingsystemservices.databinding.ActivitySensorBinding;
import com.ubtrobot.sensor.SensorDevice;
import com.ubtrobot.sensor.SensorException;
import com.ubtrobot.sensor.SensorListener;
import com.ubtrobot.sensor.SensorManager;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends BaseLogActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Manager-Sensor";

    private SensorManager mSensorManager;

    private List<String> mSpinnerData = new ArrayList<>();
    private ArrayAdapter<String> mSpinnerAdapter;
    private Spinner mSpinner;

    private String mSensorID;

    public class Param {
        public ObservableField<String> status = new ObservableField<>();
    }

    private Param mParam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySensorBinding sensorBinding = DataBindingUtil.setContentView(this, R.layout.activity_sensor);
        mParam = new Param();
        sensorBinding.setParams(mParam);

        initLoggerView();
        mSensorManager = Robot.globalContext().getSystemService(SensorManager.SERVICE);
        initSpinner();
        mHandler.post(() -> {
            addLog(TAG, "Querying sensor info ...");
            updateSensorList();

            if (mSpinnerData.size() > 0) {
                mSensorID = mSpinnerData.get(0);
                runOnUiThread(() -> mSpinner.setSelection(0));
            }
        });
    }

    private void updateSensorList() {
        mSpinnerData.clear();
        List<SensorDevice> deviceList = mSensorManager.getDeviceList();
        for (SensorDevice device : deviceList) {
            mSpinnerData.add(device.getId());
        }

        addLog(TAG, "Call updateSensorList: " + mSpinnerData.size());
        runOnUiThread(() -> mSpinnerAdapter.notifyDataSetChanged());
    }

    private void initSpinner() {
        mSpinner = findViewById(R.id.spinner);
        mSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSpinnerData);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    public void getSensorStatus(View view) {
        boolean status = mSensorManager.isEnabled(mSensorID);
        addLog(TAG, "getSensorStatus: mSensorID:" + status);
        mParam.status.set(status + "");
    }

    public void enable(View view) {
        if (TextUtils.isEmpty(mSensorID)) {
            addLog(TAG, "Call enable: error: SensorIDis empty");
            return;
        }
        addLog(TAG, "enable: " + mSensorID);
        mSensorManager.enable(mSensorID)
                .done((Void aVoid) -> addLog(TAG, "Call enable: done."))
                .fail((SensorException e) -> addLog(TAG, "Call enable: fail:" + e.getMessage()));
    }

    public void disable(View view) {
        if (TextUtils.isEmpty(mSensorID)) {
            addLog(TAG, "Call disable: error: SensorIDis empty");
            return;
        }
        addLog(TAG, "disable: " + mSensorID);
        mSensorManager.disable(mSensorID)
                .done((Void aVoid) -> addLog(TAG, "Call disable: done."))
                .fail((SensorException e) -> addLog(TAG, "Call disable: fail:" + e.getMessage()));
    }

    public void register(View view) {
        addLog(TAG, "register: " + mSensorID);
        mSensorManager.registerListener(mSensorID, sensorListener);
    }

    public void unregister(View view) {
        addLog(TAG, "unregister: ");
        mSensorManager.unregisterListener(sensorListener);
    }

    private SensorListener sensorListener = (sensorDevice, sensorEvent) -> {
        addLog(TAG, "Event ---> " + sensorEvent.toString());
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        addLog(TAG, "Selected:" + mSpinnerData.get(position));
        mSensorID = mSpinnerData.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mSensorID = "";
    }
}
