package com.ubtrobot.speech.demo.google.understand.entity;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class BehaviorStatus {

    public static final BehaviorStatus EMPTY = new BehaviorStatus();

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("details")
    private JsonArray details;

    public boolean isEmpty() {
        return code == 0 && message == null && details == null;
    }

    @Override
    public String toString() {
        return "BehaviorStatus{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", details=" + (details != null ? details.toString() : "") +
                '}';
    }
}
