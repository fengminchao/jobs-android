package com.muxistudio.jobs;

import android.app.Application;
import android.content.Context;

import android.text.TextUtils;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.db.User;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.db.UserInfo;
import com.muxistudio.jobs.db.UserInfoDao;
import com.muxistudio.jobs.injector.components.ApplicationComponent;

import com.muxistudio.jobs.injector.components.DaggerApplicationComponent;
import com.muxistudio.jobs.injector.modules.ApplicationModule;
import com.muxistudio.jobs.util.PreferenceUtil;
import java.util.List;
import javax.inject.Inject;

import okhttp3.OkHttpClient;
import org.greenrobot.greendao.query.Query;

/**
 * Created by ybao on 16/5/19.
 */
public class App extends Application {

  public static Context sContext;
  private ApplicationComponent mApplicationComponent;

  @Inject OkHttpClient mOkHttpClient;
  @Inject UserStorge mUserStorge;
  @Inject UserInfoDao mUserInfoDao;
  @Inject UserDao userDao;

  @Override public void onCreate() {
    super.onCreate();
    sContext = this;
    initInjector();
    initUser();
  }

  private void initUser() {
    String mail = PreferenceUtil.getString(PreferenceUtil.USER_MAIL);
    if (!TextUtils.isEmpty(mail)){
      Query query = userDao.queryBuilder().where(UserDao.Properties.Mail.eq(mail)).build();
      List<User> userList = query.list();
      if (userList != null && userList.size() > 0){
        mUserStorge.setUser(userList.get(0));
      }
      List<UserInfo> userInfos = mUserInfoDao.queryBuilder().where(UserInfoDao.Properties.Mail.eq(mail)).build().list();
      if (userInfos != null && userInfos.size() > 0){
        mUserStorge.setUserInfo(userInfos.get(0));
      }
    }
  }

  private void initInjector() {
    mApplicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    mApplicationComponent.inject(this);
  }

  public ApplicationComponent getApplicationComponent() {
    return mApplicationComponent;
  }
}
