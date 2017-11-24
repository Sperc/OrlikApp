package com.example.pawel.orlikapp.core;

import android.app.Application;

import com.example.pawel.orlikapp.prefs.PreferencesShared;

/**
 * Created by Pawel on 22.11.2017.
 */

public class OrlikApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesShared.initializePreferences(this);
    }
}
