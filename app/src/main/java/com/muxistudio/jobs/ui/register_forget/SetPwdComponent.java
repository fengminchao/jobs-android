package com.muxistudio.jobs.ui.register_forget;

import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by ybao on 16/10/22.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface SetPwdComponent {

    void inject(SetPwdActivity getPwdActivity);

}
