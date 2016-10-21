package com.muxistudio.jobs.injector.components;

import android.app.Activity;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by ybao on 16/10/15.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

   Activity getActivity();

}
