package com.ubtrobot.callingsystemservices;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BaseLogActivity extends AppCompatActivity {

    protected List<String> mLogData = new ArrayList<>();
    protected ArrayAdapter mLogAdapter;
    protected ListView mLogView;

    protected Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initThread();
    }

    private void initThread() {
        HandlerThread handlerThread = new HandlerThread("WorkThread");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
    }

    protected void initLoggerView() {
        mLogView = findViewById(R.id.lv_log);
        mLogAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mLogData);
        mLogView.setAdapter(mLogAdapter);
        mLogView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
    }

    protected void addLog(String tag, String log) {
        Log.i(tag, log);
        runOnUiThread(() -> {
            mLogData.add(log);
            mLogAdapter.notifyDataSetChanged();
            mLogView.setSelection(mLogAdapter.getCount() - 1);
        });
    }

    protected void clearLog() {
        runOnUiThread(() -> {
            mLogData.clear();
            mLogAdapter.notifyDataSetChanged();
        });
    }

    public void clearLog(View view) {
        clearLog();
    }
}
