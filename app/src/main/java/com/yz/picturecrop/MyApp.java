package com.yz.picturecrop;

import android.app.Application;
import android.content.Context;

/**
 * @Desc:
 * @Author: YiZe
 */
public class MyApp extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
