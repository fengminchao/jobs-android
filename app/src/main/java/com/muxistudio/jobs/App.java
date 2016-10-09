package com.muxistudio.jobs;

import android.app.Application;
import android.content.Context;

/**
 * Created by ybao on 16/5/19.
 */
public class App extends Application{

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

    }

    public static Context getContext(){
        return sContext;
    }
}
