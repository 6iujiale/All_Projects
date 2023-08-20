package com.ubtrobot.speech.demo.google.understand.service;

import org.json.JSONArray;

public class GoogleContextParams {

    private static GoogleContextParams sInstance;
    private JSONArray mContexts;

    private GoogleContextParams() {
    }

    public static GoogleContextParams get() {
        if (sInstance == null) {
            synchronized (GoogleContextParams.class) {
                if (sInstance == null) {
                    sInstance = new GoogleContextParams();
                }
            }
        }
        return sInstance;
    }

    public void saveContextList(JSONArray contextList) {
        mContexts = contextList;
    }

    public JSONArray getContextList() {
        return mContexts != null ? mContexts : new JSONArray();
    }
}
