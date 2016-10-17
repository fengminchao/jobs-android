package com.muxistudio.jobs.inject.modules;

import android.app.Activity;

import com.muxistudio.jobs.inject.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ybao on 16/10/16.
 */

@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return mActivity;
    }
}
