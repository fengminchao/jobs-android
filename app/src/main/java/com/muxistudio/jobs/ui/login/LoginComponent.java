package com.muxistudio.jobs.ui.login;

import com.muxistudio.jobs.inject.PerActivity;
import com.muxistudio.jobs.inject.components.ApplicationComponent;
import com.muxistudio.jobs.inject.modules.ActivityModule;

import dagger.Component;

/**
 * Created by ybao on 16/10/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
