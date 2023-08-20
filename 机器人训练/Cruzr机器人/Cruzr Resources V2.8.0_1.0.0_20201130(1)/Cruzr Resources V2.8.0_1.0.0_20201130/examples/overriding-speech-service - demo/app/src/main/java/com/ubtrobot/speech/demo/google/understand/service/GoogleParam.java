package com.ubtrobot.speech.demo.google.understand.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GoogleParam {

    private String lang;
    private String query;
    private String sessionId;
    private String timezone;
    private JsonArray contexts;

    public JsonObject convertToJsonObj() {
        JsonObject queryInput = new JsonObject();
        JsonObject text = new JsonObject();
        text.addProperty("text", query);
        text.addProperty("languageCode", lang);
        queryInput.add("text", text);

        JsonObject queryParams = new JsonObject();
        queryParams.addProperty("timeZone", timezone);
        queryParams.add("contexts", contexts);

        JsonObject res = new JsonObject();
        res.add("queryInput", queryInput);
        res.add("queryParams", queryParams);

        return res;
    }

    public static class Builder {

        private String lang;
        private String query;
        private String sessionId;
        private String timezone = "Asia/Shanghai";
        private JsonArray contexts;

        public Builder setLang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder setQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder setTimezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public Builder setContexts(JsonArray contexts) {
            this.contexts = contexts;
            return this;
        }

        public GoogleParam build() {
            GoogleParam param = new GoogleParam();
            param.lang = lang;
            param.query = query;
            param.sessionId = sessionId;
            param.timezone = timezone;
            param.contexts = contexts;

            return param;
        }
    }
}
