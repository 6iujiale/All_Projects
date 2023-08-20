package com.ubtrobot.callingsystemservices.light;

import android.net.Uri;

import com.ubtrobot.callingsystemservices.utils.FooUtils;

public class LightEffectUris {
    public static final Uri EFFECT_BREATHE = Uri.parse("light://effect/1");
    public static final Uri EFFECT_FLASH_ONE = Uri.parse("light://effect/2");
    public static final Uri EFFECT_FLASH_TWO = Uri.parse("light://effect/3");
    public static final Uri EFFECT_BRIGHT_3_SECOND = Uri.parse("light://effect/4");
    public static final Uri EFFECT_BRIGHT_INFINITY = Uri.parse("light://effect/5");
    public static final Uri EFFECT_WARNING = Uri.parse("light://effect/6");
    public static final Uri EFFECT_MUSIC = Uri.parse("light://effect/7");
    public static final Uri EFFECT_POWER_ON = Uri.parse("light://effect/8");
    public static final Uri EFFECT_POWER_OFF = Uri.parse("light://effect/9");

    private static Uri[] uris = {EFFECT_BREATHE, EFFECT_FLASH_ONE, EFFECT_FLASH_TWO, EFFECT_BRIGHT_3_SECOND,
            EFFECT_BRIGHT_INFINITY, EFFECT_WARNING, EFFECT_MUSIC};

    public static Uri getRandomColor() {
        return uris[FooUtils.getRandomInt(uris.length)];
    }
}
