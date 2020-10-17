package com.example.poojaapp;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class LanguageHelper {
    public static final String USER_LANGUAGE = "KEY_USER_LANGUAGE";
    public static final String APP_PREF = "AppPrefs" ;

    public static void storeUserLanguage(Context context, String language) {
        context.getSharedPreferences(APP_PREF, MODE_PRIVATE)
                .edit()
                .putString(USER_LANGUAGE, language)
                .apply();
    }
//returns null if not found
    public static String getUserLanguage(Context context) {
        return context.getSharedPreferences(APP_PREF, MODE_PRIVATE)
                .getString(USER_LANGUAGE, null);
    }
}
