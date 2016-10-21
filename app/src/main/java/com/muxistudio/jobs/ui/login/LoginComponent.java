package com.muxistudio.jobs.ui.login;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by ybao on 16/10/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
