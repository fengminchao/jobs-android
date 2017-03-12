package com.muxistudio.jobs.injector.modules;

import android.app.Activity;

import com.muxistudio.jobs.injector.PerActivity;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ybao on 16/10/16.
 */

@Module
public class ActivityModule {

    private final Activity mActivity;

    @Inject
    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }
}
