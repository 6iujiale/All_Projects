package com.ubtrobot.speech.demo.google.synthesis;

import android.util.Log;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.TextToSpeechSettings;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;
import com.ubtrobot.service.ModuleDestroyable;
import com.ubtrobot.speech.AbstractSynthesizer;
import com.ubtrobot.speech.SpeakingVoice;
import com.ubtrobot.speech.SynthesisException;
import com.ubtrobot.speech.SynthesisOption;
import com.ubtrobot.speech.SynthesisProgress;
import com.ubtrobot.speech.demo.google.token.GoogleUserCredentials;
import com.ubtrobot.speech.demo.google.token.TokenAuthorizer;
import com.ubtrobot.speech.demo.google.token.TokenUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GoogleSynthesizer extends AbstractSynthesizer implements ModuleDestroyable, GoogleTtsPlayer.GoogleTtsPlayerListener {
    private static final String TAG = "GoogleSynthesizer";

    private String SPEAKER = "en-US-Standard-B";
    private String LANGUAGE = "en-US";

    private TokenAuthorizer mTokenAuthorizer;
    private TextToSpeechClient mTtsClient;
    private GoogleTtsPlayer mGooglePlayer;

    public GoogleSynthesizer(TokenAuthorizer tokenAuthorizer) {

        if (null == tokenAuthorizer) {
            throw new IllegalArgumentException("Argument tokenAuthorizer is null.");
        }
        this.mTokenAuthorizer = tokenAuthorizer;
        mGooglePlayer = new GoogleTtsPlayer(this);

        new Thread(this::initClient).start();
    }

    private void initClient() {
        // Instantiates a client
        try {
            mTtsClient = TextToSpeechClient.create(TextToSpeechSettings.newBuilder()
                    .setCredentialsProvider(() -> new GoogleUserCredentials(mTokenAuthorizer)
                            .createScoped(TokenUtil.SCOPE))
                    .build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void startSynthesizing(SynthesisOption synthesisOption) {
        Log.i(TAG, "startSynthesizing: " + synthesisOption.toString());

        if (mGooglePlayer.isPlaying()) {
            mGooglePlayer.stop();
        }

        if (null == mTtsClient) {
            rejectSynthesizing(new SynthesisException(SynthesisException.CODE_INTERNAL_ERROR, "Synthesizer uninitialized."));
            return;
        }

        // Set the text input to be synthesized
        SynthesisInput input = SynthesisInput.newBuilder()
                .setText(synthesisOption.getInputText())
                .build();

        // Build the voice request, select the language code ("en-US") and the ssml voice gender
        // ("neutral")
        VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                .setLanguageCode(LANGUAGE)
                .setSsmlGender(SsmlVoiceGender.FEMALE)
                .build();

        // Select the type of audio file you want returned
        AudioConfig audioConfig = AudioConfig.newBuilder()
                .setAudioEncoding(AudioEncoding.MP3)
                .build();

        // Perform the text-to-speech request on the text input with the selected voice parameters and
        // audio file type
        SynthesizeSpeechResponse response = mTtsClient.synthesizeSpeech(input, voice, audioConfig);
        ByteString audioContents = null;
        if (null == response || null == (audioContents = response.getAudioContent())) {
            rejectSynthesizing(new SynthesisException(SynthesisException.CODE_REMOTE_SERVER_ERROR,
                    "Server response data is abnormal"));
            return;
        }

        mGooglePlayer.play(audioContents);
    }

    @Override
    protected void stopSynthesizing() {
        Log.i(TAG, "stopSynthesizing: ");
        if (mGooglePlayer.isPlaying()) {
            mGooglePlayer.stop();
        }
    }

    @Override
    public List<SpeakingVoice> getSpeakingVoiceList() {
        SpeakingVoice speakingVoice = new SpeakingVoice.Builder(SPEAKER)
                .setName(SPEAKER)
                .setGender(0)
                .setLanguage(LANGUAGE)
                .build();
        return Collections.singletonList(speakingVoice);
    }

    @Override
    public void destroyModule() {
        Log.i(TAG, "destroyModule: ");
        mGooglePlayer.release();
    }

    @Override
    public void onProgress(String progressState, float progress, int remainTime) {
        reportSynthesizingProgress(new SynthesisProgress
                //Progress notification type
                // 1) PROGRESS_BEGAN
                // 2) PROGRESS_PLAYING
                // 3) PROGRESS_ENDED
                .Builder(progressState)
                .setAudioBytes(null)                    // Audio data, optional
                .setPlayProgress(progress)              // Progress percentage, optional
                .setRemainingTimeMillis(remainTime)     // Remaining time, optional
                .build());
    }

    @Override
    public void onCompleted() {
        resolveSynthesizing();
    }

    @Override
    public void onError(String message) {
        rejectSynthesizing(new SynthesisException(
                SynthesisException.CODE_INTERNAL_ERROR, // Error code, can only use given
                123,                                    // Define your own error code, optional
                message));
    }
}
