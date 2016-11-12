package com.muxistudio.jobs.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.muxistudio.jobs.db.User;
import com.muxistudio.jobs.db.UserInfo;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.Jobs;

import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.db.UserInfoDao;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.db.JobsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig userInfoDaoConfig;
    private final DaoConfig collectionDaoConfig;
    private final DaoConfig jobsDaoConfig;

    private final UserDao userDao;
    private final UserInfoDao userInfoDao;
    private final CollectionDao collectionDao;
    private final JobsDao jobsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        collectionDaoConfig = daoConfigMap.get(CollectionDao.class).clone();
        collectionDaoConfig.initIdentityScope(type);

        jobsDaoConfig = daoConfigMap.get(JobsDao.class).clone();
        jobsDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);
        collectionDao = new CollectionDao(collectionDaoConfig, this);
        jobsDao = new JobsDao(jobsDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(UserInfo.class, userInfoDao);
        registerDao(Collection.class, collectionDao);
        registerDao(Jobs.class, jobsDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        userInfoDaoConfig.clearIdentityScope();
        collectionDaoConfig.clearIdentityScope();
        jobsDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

    public JobsDao getJobsDao() {
        return jobsDao;
    }

}
