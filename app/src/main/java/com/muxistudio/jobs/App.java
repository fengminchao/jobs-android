package com.muxistudio.jobs;

import android.app.Application;
import android.content.Context;

import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.injector.components.DaggerApplicationComponent;
import com.muxistudio.jobs.injector.modules.ApplicationModule;

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
        sContext = this;
        initInjector();
    }

    private void initInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

}
