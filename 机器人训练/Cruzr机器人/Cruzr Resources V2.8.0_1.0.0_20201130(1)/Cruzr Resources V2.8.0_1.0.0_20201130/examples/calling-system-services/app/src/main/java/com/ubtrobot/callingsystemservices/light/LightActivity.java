package com.ubtrobot.callingsystemservices.light;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ubtrobot.Robot;
import com.ubtrobot.callingsystemservices.BaseLogActivity;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.callingsystemservices.databinding.ActivityLightBinding;
import com.ubtrobot.callingsystemservices.utils.FooUtils;
import com.ubtrobot.light.LightColors;
import com.ubtrobot.light.LightDevice;
import com.ubtrobot.light.LightException;
import com.ubtrobot.light.LightManager;

import java.util.ArrayList;
import java.util.List;

public class LightActivity extends BaseLogActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Manager-Light";

    private LightManager mLightManager;
    private Param mParam;
    private String mCurrLightID;

    private List<String> mSpinnerData = new ArrayList<>();
    private ArrayAdapter<String> mSpinnerAdapter;
    private Spinner mSpinner;

    private int[] mColors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.WHITE};

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        addLog(TAG, "Selected:" + mSpinnerData.get(position));
        mCurrLightID = mSpinnerData.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mCurrLightID = "";
    }

    public class Param {
        public ObservableField<String> state = new ObservableField<>();
        public ObservableField<String> outColor = new ObservableField<>();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLightBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_light);
        initLoggerView();
        mParam = new Param();
        dataBinding.setParams(mParam);

        mLightManager = Robot.globalContext().getSystemService(LightManager.SERVICE);

        initSpinner();
        mHandler.post(() -> {
            addLog(TAG, "Querying light info ...");
            updateLightList();

            if (mSpinnerData.size() > 0) {
                runOnUiThread(() -> mSpinner.setSelection(0));
            }
        });
    }

    private void initSpinner() {
        mSpinner = findViewById(R.id.spinner);
        mSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSpinnerData);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    private void updateLightList() {
        mSpinnerData.clear();
        List<LightDevice> lightDevices = mLightManager.getDeviceList();
        for (LightDevice lightDevice : lightDevices) {
            mSpinnerData.add(lightDevice.getId());
        }

        runOnUiThread(() -> mSpinnerAdapter.notifyDataSetChanged());
        addLog(TAG, "Call updateLightList: " + lightDevices.size());
    }

    public void turnOn(View view) {
        addLog(TAG, "Call turnOn: ");
        mLightManager.turnOn(mCurrLightID, LightColors.GREEN)
                .done((Void aVoid) -> addLog(TAG, "Call turnOn: done."))
                .fail((LightException e) -> addLog(TAG, "Call turnOn: fail:" + e.getMessage()));
    }

    public void turnOff(View view) {
        addLog(TAG, "Call turnOff: ");
        mLightManager.turnOff(mCurrLightID)
                .done((Void aVoid) -> addLog(TAG, "turnOff: done."))
                .fail((LightException e) -> addLog(TAG, "turnOff: fail:" + e.getMessage()));
    }

    public void getLightState(View view) {
        boolean isOff = mLightManager.isTurnedOn(mCurrLightID);
        mParam.state.set(isOff ? "off" : "on");
        addLog(TAG, "Call getLightState: " + isOff);
    }

    public void getColor(View view) {
        String color = mLightManager.getColor(mCurrLightID) + "";
        mParam.outColor.set(color);
        addLog(TAG, "Call getColor: " + color);
    }

    public void setRandomColor(View view) {
        addLog(TAG, "Call setRandomColor: ");
        mLightManager.changeColor(mCurrLightID, FooUtils.getRandomFromArray(mColors))
                .done(aVoid -> addLog(TAG, "Call setRandomColor: done"))
                .fail(e -> addLog(TAG, "Call setRandomColor: fail:" + e.getMessage()));
    }

    public void setRandomEffect(View view) {
        Uri colorUri = LightEffectUris.getRandomColor();
        addLog(TAG, "Call setRandomEffect: " + colorUri.toString());
        mLightManager.displayEffect(mCurrLightID, colorUri)
                .done(aVoid -> addLog(TAG, "Call setRandomEffect: done"))
                .fail(e -> addLog(TAG, "Call setRandomEffect: fail:" + e.getMessage()));
    }
}
