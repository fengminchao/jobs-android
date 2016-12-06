package com.muxistudio.jobs.ui.main;

import android.support.v4.app.Fragment;
import android.view.MenuItem;
import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;

/**
 * Created by ybao on 16/11/7.
 */

public class MainContract {

  interface View extends BaseView{

    void showLoginUi();

    void showAccountUi();

    void showFragment(Fragment fragment);

    void showSetting();

    void showSearchView();

    void reload();

    void setTitle(String title);

    void renderAccountName(String name);

    void updateFindFragment(String key);

    void closeNavView();

    void renderAccountAvator(String url);
  }

  interface Presenter extends BasePresenter<View>{

    void onAccountClick();

    void onSearchClick();

    void onNavigationItemClick(MenuItem item);

    void exist();
  }

}
