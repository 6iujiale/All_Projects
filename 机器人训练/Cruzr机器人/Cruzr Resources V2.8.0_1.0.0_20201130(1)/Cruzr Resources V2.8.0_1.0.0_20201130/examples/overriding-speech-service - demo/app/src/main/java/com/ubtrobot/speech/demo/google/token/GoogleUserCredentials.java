package com.ubtrobot.speech.demo.google.token;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;

public class GoogleUserCredentials extends GoogleCredentials {
    TokenAuthorizer mTokenAuthorizer;

    public GoogleUserCredentials(TokenAuthorizer tokenAuthorizer) {
        super(tokenAuthorizer.authorizeToken());
        this.mTokenAuthorizer = tokenAuthorizer;
    }

    @Override
    public void refresh() throws IOException {
        super.refresh();
    }

    @Override
    public void refreshIfExpired() throws IOException {
        super.refreshIfExpired();
    }

    @Override
    public AccessToken refreshAccessToken() throws IOException {
        return mTokenAuthorizer.authorizeToken();
    }

}
