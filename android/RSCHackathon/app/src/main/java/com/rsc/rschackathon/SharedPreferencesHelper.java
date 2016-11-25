package com.rsc.rschackathon;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesHelper {

    private static final String LANGUAGE_PREFERENCE = "change_language";
    private static SharedPreferences prefs;

    public static SharedPreferences getPrefs(Context context){
        if (prefs == null) {
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return prefs;
    }

    public static String getSelectedLanguage(Context context){
        return getPrefs(context)
                .getString(LANGUAGE_PREFERENCE, "English");
    }

    public static void setSelectedLanguage(Context context, String language){
        getPrefs(context)
                .edit()
                .putString(LANGUAGE_PREFERENCE, language)
                .apply();
    }
}
