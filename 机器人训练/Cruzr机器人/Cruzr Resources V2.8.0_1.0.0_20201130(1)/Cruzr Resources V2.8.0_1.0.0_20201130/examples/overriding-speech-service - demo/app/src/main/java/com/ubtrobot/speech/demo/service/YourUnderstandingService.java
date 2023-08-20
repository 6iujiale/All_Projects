package com.ubtrobot.speech.demo.service;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.google.auth.oauth2.AccessToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.ubtrobot.async.CancelledCallback;
import com.ubtrobot.async.Deferred;
import com.ubtrobot.conversion.Converter;
import com.ubtrobot.speech.AbstractUnderstander;
import com.ubtrobot.speech.Languages;
import com.ubtrobot.speech.UnderstandingException;
import com.ubtrobot.speech.UnderstandingOption;
import com.ubtrobot.speech.UnderstandingResult;
import com.ubtrobot.speech.demo.google.token.TokenAuthorizer;
import com.ubtrobot.speech.demo.google.understand.DefaultNlpConverter;
import com.ubtrobot.speech.demo.google.understand.entity.Behavior;
import com.ubtrobot.speech.demo.google.understand.service.GoogleContextParams;
import com.ubtrobot.speech.demo.google.understand.service.GoogleParam;
import com.ubtrobot.speech.demo.google.understand.service.GoogleService;
import com.ubtrobot.speech.demo.google.understand.service.UnderstandCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is used to implement your own natural language processing function.
 * For more help, please check README.MD
 */
public class YourUnderstandingService extends AbstractUnderstander {
    private static String TAG = "YourUnderstandingService";

    private Context mContext;
    private String mProjectId;
    private TokenAuthorizer mTokenAuthorizer;
    private Call<Behavior> mUnderstandCall;
    private Converter<Behavior, UnderstandingResult> mNlpConverter;
    private Executor executor = Executors.newSingleThreadExecutor();
    AccessToken mToken;

    public YourUnderstandingService(Context context, String project_id, TokenAuthorizer tokenAuthorizer) {
        if (context == null) {
            throw new IllegalArgumentException("Argument token or projectId is null.");
        }

        if (TextUtils.isEmpty(project_id)) {
            throw new IllegalArgumentException("Argument context is null.");
        }

        if (null == tokenAuthorizer) {
            throw new IllegalArgumentException("Argument tokenAuthorizer is null.");
        }

        this.mContext = context;
        this.mProjectId = project_id;
        this.mTokenAuthorizer = tokenAuthorizer;

        mNlpConverter = new DefaultNlpConverter();
    }

    @Override
    protected void understand(UnderstandingOption option, Deferred<UnderstandingResult, UnderstandingException> deferred) {
        JSONObject paramJson = option.getParameters().getJsonObject();
        if (paramJson != null && paramJson.optBoolean("isOnlySaveContext")) {
            //Just save the context and exit
            GoogleContextParams.get().saveContextList(paramJson.optJSONArray("google"));
            deferred.resolve(UnderstandingResult.EMPTY);
            return;
        }

        executor.execute(new Runnable() {
            @Override
            public void run() {
                String seesionId = option.getSessionId();
                if (TextUtils.isEmpty(seesionId)) {
                    seesionId = UUID.randomUUID().toString();
                }
                requestSemantic(option.getInputText(), seesionId, paramJson,
                        new UnderstandCallback() {
                            @Override
                            public void successful(Behavior behavior) {
                                Log.i(TAG, "Response successful:" + behavior.toString());
                                deferred.resolve(((DefaultNlpConverter) mNlpConverter)
                                        .setInputText(option.getInputText())
                                        .convert(behavior));
                            }

                            @Override
                            public void remoteServerError(String errorMessage) {
                                Log.e(TAG, "Response server error:" + errorMessage);
                                deferred.reject(new UnderstandingException(
                                        UnderstandingException.CODE_REMOTE_SERVER_ERROR, errorMessage));
                            }

                            @Override
                            public void internalError(String errorMessage) {
                                Log.e(TAG, "Response internal error:" + errorMessage);
                                deferred.reject(new UnderstandingException(
                                        UnderstandingException.CODE_INTERNAL_ERROR, errorMessage));
                            }

                            @Override
                            public void timeoutError() {
                                Log.e(TAG, "Response timeout error.");
                                deferred.reject(new UnderstandingException(UnderstandingException.CODE_TIMEOUT));
                            }
                        });
            }
        });


        deferred.cancelled(new CancelledCallback() {
            @Override
            public void onCancelled() {
                cancelRequest();
            }
        });
    }

    private void requestSemantic(String query, String sessionId, JSONObject paramJson, UnderstandCallback callback) {
        Log.i(TAG, "Argument paramJson:" + paramJson);
        // If there are context-sensitive parameters, put them in the contexts field
        JsonArray contexts = new JsonArray();
        if (null != paramJson) {
            JSONArray array = paramJson.optJSONArray("google");
            array = array == null || array.length() == 0
                    ? GoogleContextParams.get().getContextList() : array;
            Log.d(TAG, "Google params:" + array);
            contexts = new JsonParser().parse(array.toString()).getAsJsonArray();
        }

        if (mToken == null || mToken.getExpirationTime().getTime() < System.currentTimeMillis() + 1 * 60 * 1000) {
            mToken = mTokenAuthorizer.authorizeToken();
        }

        if (null == mToken || TextUtils.isEmpty(mToken.getTokenValue())) {
            callback.internalError("AccessToken is empty.");
            return;
        }

        mUnderstandCall = getRetrofit().create(GoogleService.class)
                .understand("Bearer " + mToken.getTokenValue(), mProjectId, sessionId,
                        new GoogleParam.Builder()
                                .setLang(Languages.getSystemLanguage(mContext))
                                .setQuery(query)
                                .setSessionId(sessionId)
                                .setContexts(contexts)
                                .build()
                                .convertToJsonObj()
                );

        mUnderstandCall.enqueue(new Callback<Behavior>() {
            @Override
            public void onResponse(Call<Behavior> call, Response<Behavior> response) {
                if (response.code() != 200) {
                    String errorMessage = response.errorBody() != null
                            ? response.errorBody().toString() : "service error";
                    Log.e(TAG, "Response is not successful:" + errorMessage);
                    callback.remoteServerError(errorMessage);
                    return;
                }

                if (response.body() == null) {
                    callback.remoteServerError("Response body is null.");
                    return;
                }

                callback.successful(response.body());
            }

            @Override
            public void onFailure(Call<Behavior> call, Throwable throwable) {
                String timeoutMessage = "No address associated with hostname";
                if (timeoutMessage.contains(throwable.getMessage())) {
                    callback.timeoutError();
                } else {
                    callback.internalError(throwable.getMessage());
                }
            }
        });
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build()
                )
                .baseUrl("https://dialogflow.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void cancelRequest() {
        if (mUnderstandCall != null) {
            mUnderstandCall.cancel();
            mUnderstandCall = null;
        }
    }

}