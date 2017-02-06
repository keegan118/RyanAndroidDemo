package com.example.rhutc.ryandemo;

import android.app.Application;

import com.example.rhutc.ryandemo.util.UtilLog;

public class RyanApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UtilLog.setDebugMode(true);
    }
}
