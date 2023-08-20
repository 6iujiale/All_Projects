package com.ubtrobot.callingsystemservices.utils;

import android.text.TextUtils;

import java.util.Random;

public class FooUtils {
    private FooUtils() {
    }

    public static int string2Int(String input, int defaultValue) {
        int output = defaultValue;

        if (!TextUtils.isEmpty(input)) {
            try {
                output = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                output = defaultValue;
                e.printStackTrace();
            }
        }

        return output;
    }

    public static long string2Long(String input, long defaultValue) {
        long output = defaultValue;

        if (!TextUtils.isEmpty(input)) {
            try {
                output = Long.parseLong(input);
            } catch (NumberFormatException e) {
                output = defaultValue;
                e.printStackTrace();
            }
        }

        return output;
    }

    public static float string2Float(String input, float defaultValue) {
        float output = defaultValue;

        if (!TextUtils.isEmpty(input)) {
            try {
                output = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                output = defaultValue;
                e.printStackTrace();
            }
        }

        return output;
    }

    public static int getRandomInt(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    public static int getRandomFromArray(int[] array) {
        return array[getRandomInt(array.length)];
    }
}
