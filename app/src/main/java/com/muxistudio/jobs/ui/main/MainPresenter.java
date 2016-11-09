package com.muxistudio.jobs.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserAuth;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.ui.BaseView;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/7.
 */

public class MainPresenter implements MainContract.Presenter{

  private UserAuth mUserAuth;
  private UserDao mUserDao;
  private Context mContext;

  private MainContract.View mMainView;

  @Inject public MainPresenter(UserAuth userAuth, UserDao userDao, Context context) {
    mUserAuth = userAuth;
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
  }

  @Override public void detachView() {

  }
}
