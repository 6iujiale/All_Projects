package com.ubtrobot.speech.demo.google.understand.service;

import com.google.gson.JsonObject;
import com.ubtrobot.speech.demo.google.understand.entity.Behavior;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GoogleService {

    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("v2/projects/{projectId}/agent/sessions/{sessionId}:detectIntent")
    Call<Behavior> understand(@Header("Authorization") String authorization,
                              @Path("projectId") String projectId,
                              @Path("sessionId") String sessionId,
                              @Body JsonObject param);
}
