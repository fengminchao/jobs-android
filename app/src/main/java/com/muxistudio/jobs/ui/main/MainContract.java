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

    void showAccountUi();

    void showFragment(Fragment fragment);

    void showSearchView();

    void reload();

    void renderAccount();

  }

  interface Presenter extends BasePresenter<View>{

    void onAccountClick();

    void onSearchClick();

    void onNavigationClick(MenuItem item);

    void exist();
  }

}
