package com.ubtrobot.speech.demo.google.understand;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ubtrobot.conversion.Converter;
import com.ubtrobot.json.JsonArrayString;
import com.ubtrobot.json.JsonObjectString;
import com.ubtrobot.speech.SpeechFulfillment;
import com.ubtrobot.speech.SpeechIntent;
import com.ubtrobot.speech.UnderstandingResult;
import com.ubtrobot.speech.demo.google.understand.entity.Behavior;
import com.ubtrobot.speech.demo.google.understand.entity.BehaviorQueryResult;
import com.ubtrobot.speech.demo.google.understand.service.GoogleContextParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DefaultNlpConverter implements Converter<Behavior, UnderstandingResult> {
    private static final String TAG = "DefaultNlpConverter";

    private String mInputText;

    @Override
    public UnderstandingResult convert(Behavior behavior) {
        Log.i(TAG, "Use DefaultNlpConverter ...");
        if (TextUtils.isEmpty(mInputText)) {
            Log.e(TAG, "Field mInputText:" + mInputText);
            throw new IllegalStateException("Please call setInputText");
        }

        return new UnderstandingResult.Builder()
                .setInputText(mInputText)
                .setSource("google")
                .setLanguage(behavior.getQueryResult().getLanguageCode())
                .setSessionIncomplete(behavior.getQueryResult().isAllRequiredParamsCollected())
                .setIntent(getSpeechIntent(behavior))
                .setContextList(convertContextList(behavior))
                .setSpeechFulfillment(convertSpeechFulfillment(behavior))
                .setFulfillmentList(JsonArrayString.from(
                        getFulfillmentList(behavior).getJsonArray()
                ))
                .build();
    }

    private SpeechIntent getSpeechIntent(Behavior behavior) {
        BehaviorQueryResult result = behavior.getQueryResult();
        if (result.isEmpty()) {
            return SpeechIntent.EMPTY;
        }

        return new SpeechIntent.Builder(result.getIntent().getAction())
                .setScore(result.getIntentDetectionConfidence())
                .setParameters(JsonObjectString.from(result.getParameters().toString()))
                .build();
    }

    private SpeechFulfillment convertSpeechFulfillment(Behavior behavior) {
        return new SpeechFulfillment.Builder(SpeechFulfillment.TYPE_TEXT)
                .setText(getSpeech(behavior)).build();
    }

    private String getSpeech(Behavior behavior) {
        if (!TextUtils.isEmpty(behavior.getQueryResult().getFulfillmentText())) {
            return behavior.getQueryResult().getFulfillmentText();
        }

        JsonArray fulfillmentMessages = behavior.getQueryResult().getFulfillmentMessages();
        if (fulfillmentMessages.size() == 0) {
            return "";
        }

        for (int i = 0; i < fulfillmentMessages.size(); i++) {
            try {
                JsonObject jsonObject = fulfillmentMessages.get(i).getAsJsonObject();
                if (jsonObject.keySet().size() == 0) {
                    continue;
                }

                JsonArray textArray = jsonObject.getAsJsonObject("text")
                        .getAsJsonArray("text");
                String result = textArray.get(0).getAsString();
                if (!TextUtils.isEmpty(result)) {
                    return result;
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    private JsonArrayString getFulfillmentList(Behavior behavior) {
        String fulfillmentMessages = behavior.getQueryResult().getFulfillmentMessages().toString();

        String displayText = behavior.getQueryResult().getFulfillmentText();
        if (TextUtils.isEmpty(displayText)) {
            return JsonArrayString.from(fulfillmentMessages);
        }

        try {
            return JsonArrayString.from(new JSONArray(fulfillmentMessages)
                    .put(new JSONObject().put("displayText", displayText)));
        } catch (JSONException e) {
            // Ignore
        }

        return JsonArrayString.from(fulfillmentMessages);
    }

    private JsonArrayString convertContextList(Behavior behavior) {
        if (behavior.getQueryResult().isEmpty()) {
            return JsonArrayString.EMPTY_ARRAY;
        }

        if (TextUtils.isEmpty(getIntentName(behavior))) {
            return JsonArrayString.EMPTY_ARRAY;
        }

        JsonArray outputContexts = behavior.getQueryResult().getOutputContexts();
        if (outputContexts.size() == 0) {
            return JsonArrayString.EMPTY_ARRAY;
        }

        JSONArray res = new JSONArray();
        for (int i = 0; i < outputContexts.size(); i++) {
            String name = outputContexts.get(i).getAsJsonObject().get("name").getAsString();
            if (TextUtils.isEmpty(name)) {
                continue;
            }

            if (shouldKeep(name, getIntentName(behavior), getIntentId(behavior))) {
                try {
                    res.put(new JSONObject(outputContexts.get(i).toString()));
                } catch (JSONException e) {
                    // Ignore
                    Log.e(TAG, "JsonArray outputContexts element is not JsonObject.");
                }
            }
        }

        GoogleContextParams.get().saveContextList(res);

        return JsonArrayString.from(res);
    }

    /**
     * 判断规则
     * contextName: weather;intentName:weather-other-city
     * contextName:jokes-get-followup
     */
    private boolean shouldKeep(String contextName, String intentName, String intentId) {
        contextName = contextName.toLowerCase();
        intentName = intentName.toLowerCase();
        intentId = intentId.toLowerCase();

        if (contextName.contains("followup")) {
            return true;
        }
        if (contextName.contains(intentName)) {
            return true;
        }
        if (!TextUtils.isEmpty(intentName) && intentName.contains(contextName)) {
            return true;
        }

        if (!TextUtils.isEmpty(intentId)) {
            String[] tmp = intentId.split("/");
            String sub_id = tmp[tmp.length - 1];
            if (contextName.contains(sub_id)) {
                return true;
            }
        }

        return false;
    }

    private String getIntentName(Behavior behavior) {
        return behavior.getQueryResult().getIntent().getAction();
    }

    private String getIntentId(Behavior behavior) {
        return behavior.getQueryResult().getIntent().getName();
    }

    public DefaultNlpConverter setInputText(String inputText) {
        this.mInputText = inputText;
        return this;
    }
}
