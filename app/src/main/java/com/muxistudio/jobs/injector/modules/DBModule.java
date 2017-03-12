package com.muxistudio.jobs.injector.modules;

import android.content.Context;

import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.db.DaoMaster;
import com.muxistudio.jobs.db.DaoSession;
import com.muxistudio.jobs.db.HistoryDao;
import com.muxistudio.jobs.db.JobsDao;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.db.UserInfoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ybao on 16/11/9.
 */

@Module
public class DBModule {

    @Provides
    @Singleton
    DaoMaster.DevOpenHelper provideDevOpenHelper(Context context) {
        return new DaoMaster.DevOpenHelper(context, "jobs.db");
    }

    @Provides
    @Singleton
    DaoMaster provideDaoMaster(DaoMaster.DevOpenHelper helper) {
        return new DaoMaster(helper.getWritableDatabase());
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(DaoMaster master) {
        return master.newSession();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(DaoSession daoSession) {
        return daoSession.getUserDao();
    }

    @Provides
    @Singleton
    UserInfoDao provideUserInfo(DaoSession daoSession) {
        return daoSession.getUserInfoDao();
    }

    @Provides
    @Singleton
    CollectionDao getCollection(DaoSession daoSession) {
        return daoSession.getCollectionDao();
    }

    @Provides
    @Singleton
    JobsDao provideJob(DaoSession daoSession) {
        return daoSession.getJobsDao();
    }

    @Provides
    @Singleton
    HistoryDao provideHistory(DaoSession daoSession) {
        return daoSession.getHistoryDao();
    }

}
