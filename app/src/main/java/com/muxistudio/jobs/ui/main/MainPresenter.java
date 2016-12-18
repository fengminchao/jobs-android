package com.muxistudio.jobs.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MenuItem;
import com.muxistudio.jobs.BuildConfig;
import com.muxistudio.jobs.db.HistoryDao;
import com.muxistudio.jobs.ui.collection.CollectionFragment;
import com.muxistudio.jobs.ui.schedule.ScheduleFragment;
import com.muxistudio.jobs.ui.setting.SettingFragment;
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
  private HistoryDao mHistoryDao;

  private List<String> tagList;
  private List<String> historyList;

  private MainContract.View mMainView;

  @Inject public MainPresenter(UserStorge userStorge,UserDao userDao, Context context,HistoryDao historyDao) {
    mUserStorge = userStorge;
    mUserDao = userDao;
    mContext = context;
    mHistoryDao = historyDao;
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
        mMainView.showFragment(ScheduleFragment.newInstance());
        break;
      case R.id.action_person:
        mMainView.showFragment(CollectionFragment.newInstance());
        break;
      case R.id.action_discuss:
        break;
      case R.id.action_setting:
        mMainView.showSetting();
        break;
      case R.id.action_about:
        mUserStorge.logout();
        mMainView.showLoginUi();
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
    Logger.d(mUserStorge.isLogin() + "");
    Logger.d(mUserStorge.getUserInfo().getAvator());
    if (mUserStorge.isLogin()) {
      String url = mUserStorge.getUserInfo().getAvator();
      String name = mUserStorge.getUserInfo().getName();
      mMainView.renderAccountAvator(url);
      if (TextUtils.isEmpty(name)){
        mMainView.renderAccountName(mUserStorge.getUser().getMail());
      }else {
        mMainView.renderAccountName(mUserStorge.getUserInfo().getName());
      }
    }else{
      mMainView.renderAccountAvator("");
      mMainView.renderAccountName("未登录");
    }
  }

  @Override public void detachView() {
    super.detachView();
    mMainView = null;
  }
}
