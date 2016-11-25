package com.rsc.rschackathon.application;

import android.app.Application;
import android.content.Context;

public class RSCApp extends Application {

    private static RSCApp instance;

    public static RSCApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


}
