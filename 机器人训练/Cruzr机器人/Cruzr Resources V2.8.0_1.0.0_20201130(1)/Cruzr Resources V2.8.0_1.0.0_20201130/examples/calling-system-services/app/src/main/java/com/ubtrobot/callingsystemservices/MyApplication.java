package com.ubtrobot.callingsystemservices;

import android.app.Application;

import com.ubtechinc.cruzr.sys.cruzrleisure.leisure.LeisureManager;
import com.ubtrobot.Robot;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initApi();
    }
    private void initApi(){
        Robot.initialize(this);
        LeisureManager.get().init(this);
    }
}
