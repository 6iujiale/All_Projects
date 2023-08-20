package com.ubtrobot.speech.demo.service;

import android.util.Log;

import com.ubtrobot.async.Promise;
import com.ubtrobot.service.ModuleDestroyable;
import com.ubtrobot.speech.AbstractSynthesizer;
import com.ubtrobot.speech.SpeakingVoice;
import com.ubtrobot.speech.SynthesisException;
import com.ubtrobot.speech.SynthesisOption;
import com.ubtrobot.speech.demo.google.synthesis.GoogleSynthesizer;
import com.ubtrobot.speech.demo.google.token.TokenAuthorizer;

import java.util.List;

/**
 * This class is used to implement your own voice broadcast function.
 * For more help, please check README.MD
 */
public class YourSynthesisService extends AbstractSynthesizer implements ModuleDestroyable {
    private static final String TAG = "YourSynthesisService";

    /**
     * you can remove the YourSynthesisService and replace it with GoogleSynthesizer without using
     * this proxy. for example: {@link YourRecognitionService}
     */
    private GoogleSynthesizer mGoogleSynther;

    private Promise mPromise;

    public YourSynthesisService(TokenAuthorizer tokenAuthorizer) {
        mGoogleSynther = new GoogleSynthesizer(tokenAuthorizer);
    }

    @Override
    protected void startSynthesizing(SynthesisOption synthesisOption) {
        Log.i(TAG, "startSynthesizing: " + synthesisOption.toString());
        /** synthesisOption Parameter Description：
         *      inputText:Synthetic content;
         *      speakingVoiceId:speaker Id;
         *      speakingSpeed: 0.1~1~10, default:1;
         *      speakingVolume: 0~100, default:0;
         */

        //TODO You should add the concrete implementation code for speech synthesis here.
        mPromise = mGoogleSynther.synthesize(synthesisOption)
                .done(aVoid -> resolveSynthesizing())
                .progress(this::reportSynthesizingProgress)
                .fail(this::rejectSynthesizing);
    }

    @Override
    protected void stopSynthesizing() {
        Log.i(TAG, "stopSynthesizing: ");
        //TODO You should stop speech synthesis here
        if (mPromise != null) {
            mPromise.cancel();
        }
    }

    @Override
    public List<SpeakingVoice> getSpeakingVoiceList() {
        //TODO If you have multiple speakers, you can give a list of supported speakers here.
        return mGoogleSynther.getSpeakingVoiceList();
    }

    @Override
    public void destroyModule() {
        Log.i(TAG, "destroyModule: ");
        if (mGoogleSynther != null && mPromise != null) {
            rejectSynthesizing(new SynthesisException(SynthesisException.CODE_INTERNAL_ERROR));
        }
        if (mGoogleSynther instanceof ModuleDestroyable) {
            ((ModuleDestroyable) mGoogleSynther).destroyModule();
        }
    }
}