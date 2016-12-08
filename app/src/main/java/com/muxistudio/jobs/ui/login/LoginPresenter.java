package com.muxistudio.jobs.ui.login;

import com.muxistudio.jobs.db.UserInfo;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.User;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.db.UserInfoDao;
import com.muxistudio.jobs.injector.PerActivity;

import com.muxistudio.jobs.ui.SubscriptionPresenter;
import com.muxistudio.jobs.util.MD5Util;
import com.muxistudio.jobs.util.PreferenceUtil;
import com.muxistudio.jobs.util.ToastUtil;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.inject.Inject;

import org.greenrobot.greendao.query.Query;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/10/19.
 */

@PerActivity public class LoginPresenter extends SubscriptionPresenter
    implements LoginContract.Presenter {

  private UserApi mUserApi;
  @Inject UserStorge mUserStorge;
  @Inject UserDao mUserDao;
  @Inject UserInfoDao mUserInfoDao;

  private LoginContract.View mLoginView;

  @Inject public LoginPresenter(UserApi userApi, UserStorge userStorge) {
    Logger.d(mUserApi == null ? "userapi is null" : "userapi is not null");
    mUserApi = userApi;
    mUserStorge = userStorge;
  }

  @Override public void attachView(LoginContract.View view) {
    mLoginView = view;
  }

  @Override public void login(String mail, String pwd) {
    User user = new User();
    user.setMail(mail);
    try {
      user.setPwd(MD5Util.getMD5(pwd));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    mUserStorge.setUser(user);
    Subscription s = mUserApi.getUserService()
        .login(mUserStorge.getUser())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(tokenResult -> {
          if (tokenResult.code == 0) {
            mUserStorge.setToken(tokenResult.token);
            mUserStorge.setUser(user);
            PreferenceUtil.putString(PreferenceUtil.USER_MAIL,mUserStorge.getUser().getMail());
            insertOrUpdateUser(mUserStorge.getUser());
            getuserInfo();
          }
        }, throwable -> {
          throwable.printStackTrace();
          ToastUtil.showShort("邮箱或密码错误");
        });
    addSubscription(s);
  }

  private void getuserInfo() {
    Subscription s = mUserApi.getUserService()
        .getUserInfo()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(userInfoResult -> {
          mLoginView.showMainActivity();
          mUserStorge.setUserInfo(userInfoResult.data);
          insertUserIfNotExist(userInfoResult.data);
        }, throwable -> {
          throwable.printStackTrace();
        });
    addSubscription(s);
  }

  private void insertUserIfNotExist(UserInfo data) {
    List<UserInfo> userInfos = mUserInfoDao.queryBuilder().where(UserInfoDao.Properties.Mail.eq(data.getMail())).list();
    if (userInfos.isEmpty()){
      mUserInfoDao.insert(data);
    }
  }

  private void insertOrUpdateUser(User user){
    List<User> users = mUserDao.queryBuilder().where(UserDao.Properties.Mail.eq(user.getMail())).list();
    if (users.isEmpty()){
      mUserDao.insert(user);
    }else {
      mUserDao.update(user);
    }
  }

}
