package com.muxistudio.jobs.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MenuItem;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.SubscriptionPresenter;
import com.muxistudio.jobs.ui.find.FindFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.schedulers.Schedulers;

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
    mMainView.closeNavView();
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
    List<Integer> list = new ArrayList<>();
    list.add(new Integer(2));
    list.add(new Integer(1));
    list.add(new Integer(6));
    list.add(new Integer(3));
    list.add(new Integer(7));
    list.add(new Integer(2));
    Observable.from(list)
        .subscribeOn(Schedulers.immediate())
        .observeOn(Schedulers.immediate())
        .toSortedList((num,num2) -> num2.compareTo(num))
        .subscribe(list1 -> {
          for (int i = 0;i < list1.size();i ++){
            Logger.d(list1.get(i) + "");
          }
        },throwable -> throwable.printStackTrace());
  }

  private void initUserInfo() {
    if (mUserStorge.isLogin()) {
      String url = mUserStorge.getUserInfo().getAvator();
      String name = mUserStorge.getUserInfo().getName();
      mMainView.renderAccountAvator(url);
      if (TextUtils.isEmpty(name)){
        mMainView.renderAccountName(mUserStorge.getUser().getMail());
      }else {
        mMainView.renderAccountName(mUserStorge.getUserInfo().getName());
      }
      return;
    }
  }

  @Override public void detachView() {
    super.detachView();
    mMainView = null;
  }
}
