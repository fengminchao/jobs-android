package com.muxistudio.jobs.injector.components;

import android.content.Context;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.api.UserAuth;
import com.muxistudio.jobs.api.jobs.JobsApi;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.injector.modules.ApiModule;
import com.muxistudio.jobs.injector.modules.ApplicationModule;
import com.muxistudio.jobs.ui.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ybao on 16/10/15.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    Context getContext();

    UserApi getUserApi();

    JobsApi getJobsApi();

    UserAuth getUserAuth();

    void inject(App application);

    void inject(BaseActivity baseActivity);
}
