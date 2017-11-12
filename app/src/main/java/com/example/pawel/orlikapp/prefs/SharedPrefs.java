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

    public void storeUser(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

    public void storeToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();    }
    public void storeLogin(String login){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login",login);
        editor.apply();
    }
    public void storeLoginCheckbox(String flag){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("loginCheckbox",flag);
        editor.apply();
    }
    public String readString(String key) {
        return sharedPreferences.getString(key, "");
    }
    public void deleteString(String key){
        sharedPreferences.edit().remove(key).apply();
    }

}
