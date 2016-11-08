package com.muxistudio.jobs.ui.main;

import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;

/**
 * Created by ybao on 16/11/7.
 */

public class MainContract {

  interface View extends BaseView{

    void showAccountUi();

    void showFragment();

    void showSearchView();

    void reload();

    void renderAccount();

  }

  interface Presenter extends BasePresenter{

    void onAccountClick();

    void onSearchClick();

    void onNavigationClick();

    void exist();
  }

}
