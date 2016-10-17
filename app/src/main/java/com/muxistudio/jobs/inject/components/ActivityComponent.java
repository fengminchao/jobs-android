package com.muxistudio.jobs.inject.components;

import android.app.Activity;

import com.muxistudio.jobs.inject.PerActivity;
import com.muxistudio.jobs.inject.modules.ActivityModule;

import dagger.Component;

/**
 * Created by ybao on 16/10/15.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

}
