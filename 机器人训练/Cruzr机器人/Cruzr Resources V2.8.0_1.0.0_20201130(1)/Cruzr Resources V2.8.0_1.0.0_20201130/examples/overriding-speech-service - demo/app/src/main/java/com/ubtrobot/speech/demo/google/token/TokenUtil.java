package com.ubtrobot.speech.demo.google.token;

import android.content.Context;
import android.util.Log;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class TokenUtil {
    private static final String TAG = "TokenUtil";
    public static final List<String> SCOPE =
            Collections.singletonList("https://www.googleapis.com/auth/cloud-platform");

    /**
     * @param context
     * @param rawResId Json file downloaded from google account
     * @return
     */
    public static AccessToken getTokenFromFile(Context context, int rawResId) {
        final InputStream stream = context.getResources().openRawResource(rawResId);
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream)
                    .createScoped(SCOPE);
            final AccessToken token = credentials.refreshAccessToken();
            Log.i(TAG, "getTokenFromFile: " + ((token == null) ? "null" : token.toString()));
            return token;
        } catch (IOException e) {
            Log.e(TAG, "Failed to obtain access token.", e);
            return null;
        }
    }
}
