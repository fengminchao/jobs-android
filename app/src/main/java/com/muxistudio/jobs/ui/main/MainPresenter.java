package com.muxistudio.jobs.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;
import android.view.MenuItem;
import com.muxistudio.jobs.App;
import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.SubscriptionPresenter;
import com.muxistudio.jobs.ui.find.FindFragment;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/7.
 */

@PerActivity public class MainPresenter extends SubscriptionPresenter implements MainContract.Presenter{

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
    Logger.d(mMainView == null ? "mainview is null" : "mainview not null");
    if (mUserStorge.isLogin()){
      mMainView.showAccountUi();
      return;
    }
    mMainView.showLoginUi();
  }

  @Override public void onSearchClick() {

  }

  @Override public void onNavigationItemClick(MenuItem item) {
    switch (item.getItemId()){
      case R.id.action_find:
        mMainView.showFragment(FindFragment.newInstance());
        break;
      case R.id.action_date:
        //mMainView.showFragment();
        break;
      case R.id.action_person:
        break;
      case R.id.action_discuss:
        break;
      case R.id.action_setting:
        break;
      case R.id.action_about:
        break;
    }
    mMainView.setTitle(item.getTitle().toString());
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
      if (TextUtils.isEmpty(url)) {
        mMainView.renderAccount(url,name);
      }
    }
  }

  @Override public void detachView() {
    super.detachView();
    mMainView = null;
  }
}
