package com.muxistudio.jobs;

import android.app.Application;
import android.content.Context;

import com.muxistudio.jobs.inject.components.ApplicationComponent;
import com.muxistudio.jobs.inject.components.DaggerApplicationComponent;
import com.muxistudio.jobs.inject.modules.ApplicationModule;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by ybao on 16/5/19.
 */
public class App extends Application{

    public static Context sContext;
    private ApplicationComponent mApplicationComponent;

    @Inject
    OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        initInjector();
    }

    private void initInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public static Context getContext(){
        return sContext;
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

}
