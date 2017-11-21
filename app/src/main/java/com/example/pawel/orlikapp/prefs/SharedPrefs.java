package com.example.pawel.orlikapp.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Pawel on 11.11.2017.
 */

public class SharedPrefs {
    //    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPrefs(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void onStoreData(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String onReadString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void onDeleteString(String key) {
        sharedPreferences.edit().remove(key).apply();
    }


}
