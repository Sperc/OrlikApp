package com.example.pawel.orlikapp.utils;

import android.content.Context;
import android.util.Log;

import com.example.pawel.orlikapp.BuildConfig;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

/**
 * Created by Pawel on 11.11.2017.
 */

public class Logs {
    public static void d(String TAG, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
