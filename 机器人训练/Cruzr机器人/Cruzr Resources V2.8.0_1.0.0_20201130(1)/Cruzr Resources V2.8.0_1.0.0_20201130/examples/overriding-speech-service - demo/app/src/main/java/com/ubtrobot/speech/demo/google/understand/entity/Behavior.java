package com.ubtrobot.speech.demo.google.understand.entity;

import com.google.gson.annotations.SerializedName;

public class Behavior {

    @SerializedName("responseId")
    private String responseId;
    @SerializedName("queryResult")
    private BehaviorQueryResult queryResult;
    @SerializedName("webhookStatus")
    private BehaviorStatus status;

    public String getResponseId() {
        return responseId != null ? responseId : "";
    }

    public BehaviorQueryResult getQueryResult() {
        return queryResult != null ? queryResult : BehaviorQueryResult.EMPTY;
    }

    public BehaviorStatus getStatus() {
        return status != null ? status : BehaviorStatus.EMPTY;
    }

    @Override
    public String toString() {
        return "Behavior{" +
                "responseId='" + responseId + '\'' +
                ", queryResult=" + (queryResult != null ? queryResult.toString() : "") +
                ", status=" + (status != null ? status.toString() : "") +
                '}';
    }
}
