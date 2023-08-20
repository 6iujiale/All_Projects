package com.ubtrobot.callingsystemservices.diagnosis;

import android.databinding.DataBindingUtil;
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
import com.ubtrobot.diagnosis.Diagnosis;
import com.ubtrobot.diagnosis.DiagnosisListener;
import com.ubtrobot.diagnosis.DiagnosisManager;
import com.ubtrobot.part.Part;
import com.ubtrobot.part.PartManager;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisActivity extends BaseLogActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Manager-Diagnosis";

    private static final String ALL = "all";

    private PartManager mPartManager;
    private DiagnosisManager mDiagnosisManager;
    private DiagnosisListener mListener;

    private List<String> mSpinnerData = new ArrayList<>();
    private ArrayAdapter<String> mSpinnerAdapter;
    private Spinner mSpinner;

    private String mCurrPartID;
    private boolean isRegistered;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_diagnosis);
        initLoggerView();
        mPartManager = Robot.globalContext().getSystemService(PartManager.SERVICE);
        mDiagnosisManager = Robot.globalContext().getSystemService(DiagnosisManager.SERVICE);
        mListener = (Diagnosis diagnosis) -> addLog(TAG, diagnosis.toString());

        initSpinner();
        mHandler.post(() -> {
            addLog(TAG,"Querying part info ...");
            updatePartList();

            if (mSpinnerData.size() > 0) {
                mCurrPartID = mSpinnerData.get(0);
                runOnUiThread(() -> mSpinner.setSelection(0));
            }
        });
    }

    private void updatePartList() {
        mSpinnerData.clear();
        mSpinnerData.add(ALL);
        List<Part> partList = mPartManager.getPartList();
        for (Part part : partList) {
            mSpinnerData.add(part.getId());
        }

        addLog(TAG, "Call updatePartList: " + (mSpinnerData.size() - 1));
        runOnUiThread(() -> mSpinnerAdapter.notifyDataSetChanged());
    }

    private void initSpinner() {
        mSpinner = findViewById(R.id.spinner);
        mSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSpinnerData);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    public void register(View view) {
        if (isRegistered) {
            isRegistered = false;
            mDiagnosisManager.unregisterListener(mListener);
        }

        if (TextUtils.isEmpty(mCurrPartID)) {
            addLog(TAG, "Call error,Please set partId first.");
            return;
        }

        addLog(TAG, "Call register: " + mCurrPartID);
        isRegistered = true;
        if (ALL.equals(mCurrPartID)) {
            mDiagnosisManager.registerListener(mListener, mSpinnerData);
        } else {
            mDiagnosisManager.registerListener(mListener, mCurrPartID);
        }
    }

    public void unregister(View view) {
        addLog(TAG, "Call unregister: ");
        isRegistered = false;
        mDiagnosisManager.unregisterListener(mListener);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        addLog(TAG, "Selected:" + mSpinnerData.get(position));
        mCurrPartID = mSpinnerData.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mCurrPartID = ALL;
    }
}
