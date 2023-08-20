package com.ubtrobot.speech.demo.google.understand.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class BehaviorQueryResult {

    public static final BehaviorQueryResult EMPTY = new BehaviorQueryResult();

    @SerializedName("queryText")
    private String queryText;
    @SerializedName("languageCode")
    private String languageCode;
    @SerializedName("speechRecognitionConfidence")
    private float speechRecognitionConfidence;
    @SerializedName("action")
    private String action;
    @SerializedName("parameters")
    private JsonObject parameters;
    @SerializedName("allRequiredParamsCollected")
    private boolean allRequiredParamsCollected;
    @SerializedName("fulfillmentText")
    private String fulfillmentText;
    @SerializedName("fulfillmentMessages")
    private JsonArray fulfillmentMessages;
    @SerializedName("webhookSource")
    private String webhookSource;
    @SerializedName("webhookPayload")
    private JsonObject webhookPayload;
    @SerializedName("outputContexts")
    private JsonArray outputContexts;
    @SerializedName("intent")
    private QueryResultIntent intent;
    @SerializedName("intentDetectionConfidence")
    private float intentDetectionConfidence;
    @SerializedName("diagnosticInfo")
    private JsonObject diagnosticInfo;
    @SerializedName("sentimentAnalysisResult")
    private JsonObject sentimentAnalysisResult;
    @SerializedName("knowledgeAnswers")
    private JsonObject knowledgeAnswers;

    public String getWebhookSource() {
        return webhookSource != null ? webhookSource : "";
    }

    public String getQueryText() {
        return queryText != null ? queryText : "";
    }

    public String getAction() {
        return action != null ? action : "";
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public boolean isAllRequiredParamsCollected() {
        return allRequiredParamsCollected;
    }

    public JsonObject getParameters() {
        return parameters != null ? parameters : new JsonObject();
    }

    public JsonArray getOutputContexts() {
        return outputContexts != null ? outputContexts : new JsonArray();
    }

    public JsonArray getFulfillmentMessages() {
        return fulfillmentMessages != null ? fulfillmentMessages : new JsonArray();
    }

    public String getFulfillmentText() {
        return fulfillmentText != null ? fulfillmentText : "";
    }

    public float getIntentDetectionConfidence() {
        return intentDetectionConfidence;
    }

    public QueryResultIntent getIntent() {
        return intent != null ? intent : QueryResultIntent.EMPTY;
    }

    public boolean isEmpty() {
        return parameters == null && outputContexts == null && intent == null;
    }

    @Override
    public String toString() {
        return "BehaviorQueryResult{" +
                "webhookSource='" + webhookSource + '\'' +
                ", queryText='" + queryText + '\'' +
                ", action='" + action + '\'' +
                ", allRequiredParamsCollected=" + allRequiredParamsCollected +
                ", parameters=" + (parameters != null ? parameters.toString() : "") +
                ", outputContexts=" + (outputContexts != null ? outputContexts.toString() : "") +
                ", fulfillmentMessages=" + (fulfillmentMessages != null ? fulfillmentMessages.toString() : "") +
                ", fulfillmentText='" + fulfillmentText + '\'' +
                ", intentDetectionConfidence=" + intentDetectionConfidence +
                ", intent=" + (intent != null ? intent.toString() : "") +
                '}';
    }
}
