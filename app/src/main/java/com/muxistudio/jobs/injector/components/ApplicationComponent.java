package com.muxistudio.jobs.injector.components;

import android.content.Context;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.jobs.JobsApi;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.db.JobsDao;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.db.UserInfoDao;
import com.muxistudio.jobs.injector.modules.ApiModule;
import com.muxistudio.jobs.injector.modules.ApplicationModule;
import com.muxistudio.jobs.injector.modules.DBModule;
import com.muxistudio.jobs.ui.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ybao on 16/10/15.
 */

@Singleton @Component(modules = { ApplicationModule.class, ApiModule.class, DBModule.class })
public interface ApplicationComponent {

  Context getContext();

  UserApi getUserApi();

  JobsApi getJobsApi();

  UserStorge getUserStorge();

  UserDao getUserDao();

  JobsDao getJobsDao();

  UserInfoDao getInfoDao();

  CollectionDao getCollectionDao();

  void inject(App application);

  void inject(BaseActivity baseActivity);
}
