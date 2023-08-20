package com.ubtrobot.speech.demo.google.understand.service;

import com.ubtrobot.speech.demo.google.understand.entity.Behavior;

public interface UnderstandCallback {

    void successful(Behavior behavior);

    void remoteServerError(String errorMessage);

    void internalError(String errorMessage);

    void timeoutError();
}
