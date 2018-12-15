package com.example.nex4jmq.paytackapp.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private static final String otp = "otp";

    private static final String PREFS_NAME = "PayTack";

    public static void saveOTP(Context context, String value) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(otp, value);
            editor.apply();
        }
    }

    public static String getOTP(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(otp, "");
    }


}