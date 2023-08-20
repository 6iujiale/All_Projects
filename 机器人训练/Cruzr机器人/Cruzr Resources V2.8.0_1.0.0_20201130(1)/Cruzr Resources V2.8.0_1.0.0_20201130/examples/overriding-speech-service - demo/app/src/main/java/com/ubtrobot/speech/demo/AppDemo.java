package com.ubtrobot.speech.demo;

import android.app.Application;

import com.google.auth.oauth2.AccessToken;
import com.ubtrobot.service.ServiceModules;
import com.ubtrobot.speech.Recognizer;
import com.ubtrobot.speech.Synthesizer;
import com.ubtrobot.speech.Understander;
import com.ubtrobot.speech.demo.google.token.TokenAuthorizer;
import com.ubtrobot.speech.demo.google.token.TokenUtil;
import com.ubtrobot.speech.demo.service.YourRecognitionService;
import com.ubtrobot.speech.demo.service.YourSynthesisService;
import com.ubtrobot.speech.demo.service.YourUnderstandingService;

import android.util.Log;

public class AppDemo extends Application {

    // TODO Fill in your authorization information
    /**
     * NLP authorization information
     */
    private static final String YOUR_GOOGLE_NLP_PROJECT_ID = "";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("appdemo", "onCreate...");
        //TODO Initialization service
        ServiceModules.initialize(this);
        createServices();
    }

    private void createServices() {
        // TODO: The way to get Google's authorization token shows in the following code.
        //  You need to download the token json file from Google Cloud Platform and put
        //  it in the res/raw directory.
        //AccessToken asrToken = TokenUtil.getTokenFromFile(getApplicationContext(), R.raw.your_google_asr_account_info);
        // AccessToken nlpToken = TokenUtil.getTokenFromFile(getApplicationContext(), R.raw.your_google_nlp_account_info);
        // AccessToken ttsToken = TokenUtil.getTokenFromFile(getApplicationContext(), R.raw.your_google_tts_account_info);
        //TODO Declare speech recognition service
        ServiceModules.declare(
                Recognizer.class,
                (aClass, notifier) -> notifier.notifyModuleCreated(new YourRecognitionService(new TokenAuthorizer() {
                    @Override
                    public AccessToken authorizeToken() {
                        //return asrToken;
                        Log.d("appdemo", "authorizeToken asr");
                        return TokenUtil.getTokenFromFile(getApplicationContext(), R.raw.your_google_asr_account_info);
                    }
                }))
        );

        //TODO Declare speech synthesis service
        ServiceModules.declare(
                Synthesizer.class,
                (aClass, notifier) -> notifier.notifyModuleCreated(new YourSynthesisService(new TokenAuthorizer() {
                    @Override
                    public AccessToken authorizeToken() {
                        // return ttsToken;
                        Log.d("appdemo", "authorizeToken tts");
                        return TokenUtil.getTokenFromFile(getApplicationContext(), R.raw.your_google_tts_account_info);
                    }
                }))
        );

        //TODO Declare speech understanding service
        ServiceModules.declare(
                Understander.class,
                (aClass, notifier) -> notifier.notifyModuleCreated(new YourUnderstandingService(getApplicationContext(),
                        YOUR_GOOGLE_NLP_PROJECT_ID, new TokenAuthorizer() {
                    @Override
                    public AccessToken authorizeToken() {
                        //return nlpToken;
                        Log.d("appdemo", "authorizeToken nlp");
                        return TokenUtil.getTokenFromFile(getApplicationContext(), R.raw.your_google_nlp_account_info);
                    }
                }))
        );
    }

}
