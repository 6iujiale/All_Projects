package com.ubtrobot.speech.demo.google.recognition;

public interface RecognizeListener {
    void onVolume(int volume);

    void onTimeout();

    void onError(int code);

    void onResult(String text);
}
