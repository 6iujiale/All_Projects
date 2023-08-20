package com.ubtrobot.cruzr.leisure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ubtechinc.cruzr.sys.cruzrleisure.entity.Leisure;
import com.ubtechinc.cruzr.sys.cruzrleisure.entity.LeisureOptions;
import com.ubtechinc.cruzr.sys.cruzrleisure.leisure.LeisureManager;
import com.ubtrobot.async.DoneCallback;
import com.ubtrobot.async.FailCallback;
import com.ubtrobot.callingsystemservices.R;

import java.util.List;

public class LeisureActivity extends AppCompatActivity {
    private TextView mMessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leisure);
        mMessageText = findViewById(R.id.showmessage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onclick(View v) {
        if(!LeisureManager.get().isConnected()){
            return;
        }
        switch (v.getId()) {
            case R.id.open:
                //enable the "testleisure" ,the default status is close
                LeisureOptions options = new LeisureOptions.Builder().key("testleisure").open(true).build();
                LeisureManager.get().update(options).done(new DoneCallback<Void>() {
                    @Override
                    public void onDone(Void aVoid) {
                        Leisure leisure = LeisureManager.get().getLeisure("testleisure");
                        if (leisure != null) {
                            showMessage("Open ret =  " + leisure.isOpen() + " leisure time =  " + leisure.getTime());
                        }
                    }
                }).fail(new FailCallback<Exception>() {
                    @Override
                    public void onFail(Exception e) {
                        showMessage("Open failed");
                    }
                });
                break;
            case R.id.getleisure:
                Leisure leisure = LeisureManager.get().getLeisure("testleisure");
                if (leisure != null) {
                    showMessage("LeisureOptions  key = " + leisure.getKey() + " time = " + leisure.getTime() + " enable state =" + leisure.isOpen());
                } else {
                    showMessage("getLeisure failed");
                }
                break;
            case R.id.getleisures:
                List<Leisure> leisures = LeisureManager.get().getLeisures();
                if (leisures != null) {
                    showMessage("getAllLeisures size = " + leisures.size() );
                } else {
                    showMessage("getAllLeisures failed");
                }
                break;
            case R.id.prohibitedleisure:
                LeisureManager.get().prohibitedLeisure();
                showMessage("After the setting will not enter the idle, all the idle tasks will not start the task");
                break;
            case R.id.unprohibitedleisure:
                LeisureManager.get().unProhibitedLeisure();
                showMessage("Set the timer again, and when the time is up, the corresponding idle task will be started");
                break;
            case R.id.update:
                //et key for testleisure's idle task entry time to 20 seconds
                LeisureOptions options1 = new LeisureOptions.Builder().key("testleisure").time(20).build();
                LeisureManager.get().update(options1).done(new DoneCallback<Void>() {
                    @Override
                    public void onDone(Void aVoid) {
                        Leisure leisure = LeisureManager.get().getLeisure("testleisure");
                        if (leisure != null) {
                            showMessage("Update the time to  " + leisure.getTime());
                        }
                    }
                }).fail(new FailCallback<Exception>() {
                    @Override
                    public void onFail(Exception e) {
                        showMessage("update the time failed");
                    }
                });
                break;
            case R.id.wakeup:
                LeisureManager.get().wakeup();
                showMessage("Wake up will reset the timer");
                break;
        }
    }

    private void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMessageText.setText(message);
            }
        });
    }

}
