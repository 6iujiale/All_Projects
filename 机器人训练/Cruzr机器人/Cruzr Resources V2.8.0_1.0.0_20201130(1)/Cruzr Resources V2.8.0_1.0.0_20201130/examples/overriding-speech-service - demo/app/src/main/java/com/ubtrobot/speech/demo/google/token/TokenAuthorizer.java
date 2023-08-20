package com.ubtrobot.speech.demo.google.token;

import com.google.auth.oauth2.AccessToken;

public abstract class TokenAuthorizer {
    public abstract AccessToken authorizeToken();
}

