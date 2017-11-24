package com.example.pawel.orlikapp.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Pawel on 24.11.2017.
 */

public class PreferencesShared {
    private static SharedPreferences sharedPreferences = null;
    public static void initializePreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return;
    }

    public static void onStoreData(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String onReadString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static void onDeleteString(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

}
