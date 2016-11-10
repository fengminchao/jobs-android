package com.muxistudio.jobs.injector.modules;

import android.content.Context;
import com.muxistudio.jobs.db.DaoMaster;
import com.muxistudio.jobs.db.DaoSession;
import com.muxistudio.jobs.db.UserDao;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by ybao on 16/11/9.
 */

@Module
public class DBModule {

  @Provides @Singleton DaoMaster.DevOpenHelper provideDevOpenHelper(Context context){
    return new DaoMaster.DevOpenHelper(context,"jobs.db");
  }

  @Provides @Singleton DaoMaster provideDaoMaster(DaoMaster.DevOpenHelper helper){
    return new DaoMaster(helper.getWritableDatabase());
  }

  @Provides @Singleton DaoSession provideDaoSession(DaoMaster master){
    return master.newSession();
  }

  @Provides @Singleton UserDao provideUserDao(DaoSession daoSession){
    return daoSession.getUserDao();
  }
}
