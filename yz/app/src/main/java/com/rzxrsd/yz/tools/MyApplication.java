package com.rzxrsd.yz.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by 李壮壮 on 2018/6/2.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}