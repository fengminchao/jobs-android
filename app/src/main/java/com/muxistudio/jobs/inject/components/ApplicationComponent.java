package com.muxistudio.jobs.inject.components;

import android.app.Application;
import android.content.Context;

import com.muxistudio.jobs.base.BaseActivity;
import com.muxistudio.jobs.inject.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ybao on 16/10/15.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();

    void inject(Application application);

    void inject(BaseActivity baseActivity);
}
