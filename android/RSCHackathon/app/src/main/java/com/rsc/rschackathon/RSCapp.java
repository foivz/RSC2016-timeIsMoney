package com.rsc.rschackathon;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.orm.SugarContext;

public class RSCapp extends Application{
    private static Context context;

    public void onCreate() {
        super.onCreate();
        RSCapp.context = getApplicationContext();
        SugarContext.init(RSCapp.getAppContext());
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }

    public static Context getAppContext() {
        return RSCapp.context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }
}
