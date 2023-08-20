package com.ubtrobot.speech.demo.google.understand.entity;

import com.google.gson.annotations.SerializedName;

public class QueryResultIntent {

    public static final QueryResultIntent EMPTY = new QueryResultIntent();

    @SerializedName("name")
    private String name;
    @SerializedName("webhookState")
    private String webhookState;
    @SerializedName("displayName")
    private String action;

    public String getName() {
        return name != null ? name : "";
    }

    public String getWebhookState() {
        return webhookState != null ? webhookState : "";
    }

    public String getAction() {
        return action != null ? action : "";
    }

    @Override
    public String toString() {
        return "QueryResultIntent{" +
                "name='" + name + '\'' +
                ", webhookState='" + webhookState + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
