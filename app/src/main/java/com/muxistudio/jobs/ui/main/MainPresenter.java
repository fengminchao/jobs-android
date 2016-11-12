package com.muxistudio.jobs.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import com.muxistudio.jobs.App;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.db.UserDao;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/7.
 */

public class MainPresenter implements MainContract.Presenter{

  private UserDao mUserDao;
  private Context mContext;
  private UserStorge mUserStorge;

  private MainContract.View mMainView;

  @Inject public MainPresenter(UserStorge userStorge,UserDao userDao, Context context) {
    mUserStorge = userStorge;
    mUserDao = userDao;
    mContext = context;
  }

  @Override public void onAccountClick() {

  }

  @Override public void onSearchClick() {

  }

  @Override public void onNavigationClick(MenuItem item) {
    switch (item.getItemId()){
      //case R.id.
    }
  }

  @Override public void exist() {

  }

  @Override public void attachView(@NonNull MainContract.View view) {
    mMainView = view;
    initUserInfo();
  }

  private void initUserInfo() {
    if (mUserStorge.isLogin()) {
      String url = mUserStorge.getUserInfo().getAvator();
      String name = mUserStorge.getUserInfo().getName();
      mMainView.renderAccount(url,name);
    }
  }

  @Override public void detachView() {

  }
}
