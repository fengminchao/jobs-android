package com.muxistudio.jobs.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.injector.HasComponent;
import com.muxistudio.jobs.ui.BaseActivity;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.ui.find.FindFragment;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/1.
 */

public class MainActivity extends BaseActivity
    implements MainContract.View, HasComponent<MainComponent>,NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.content) FrameLayout content;
  @BindView(R.id.nav_view) NavigationView navView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.toolbar) Toolbar mToolbar;

  //指示当前的 fragment 是否是findfragment
  private boolean isFindFragment = true;

  private MainComponent mMainComponent;
  @Inject MainPresenter mPresenter;

  @Override protected void initView() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    showFragment(new FindFragment());
    mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
    mToolbar.setTitle("招聘");
    setSupportActionBar(mToolbar);
    //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //getSupportActionBar().setDisplayShowHomeEnabled(false);
    navView.setNavigationItemSelectedListener(this);
    mPresenter.attachView(this);
  }

  @Override protected void initInjector() {
    mMainComponent = DaggerMainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .mainModule(new MainModule())
        .build();
    mMainComponent.inject(this);
  }


  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    menu.clear();
    Logger.d("is findfragmnet? " + isFindFragment);
    if (isFindFragment) {
      getMenuInflater().inflate(R.menu.menu_search, menu);
    }
    return super.onPrepareOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(Gravity.LEFT);
        return true;
      case R.id.action_search:
        mPresenter.onSearchClick();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    mPresenter.onNavigationClick(item);
    return true;
  }

  @Override public void showAccountUi() {

  }

  @Override public void showFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    Logger.d((fragment instanceof FindFragment) + "");
    if (fragment instanceof FindFragment) {
      invalidateOptionsMenu();
    }
  }

  @Override public void showSearchView() {

  }

  @Override public void renderAccount() {

  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }

  @Override public MainComponent getComponent() {
    return mMainComponent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }
}
