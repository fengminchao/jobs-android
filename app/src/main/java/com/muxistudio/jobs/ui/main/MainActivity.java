package com.muxistudio.jobs.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.injector.HasComponent;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.ui.accout.AccountActivity;
import com.muxistudio.jobs.ui.find.FindFragment;
import com.muxistudio.jobs.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/1.
 */

public class MainActivity extends ToolbarActivity
    implements MainContract.View, HasComponent<MainComponent>,
    NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.content) FrameLayout content;
  @BindView(R.id.nav_view) NavigationView navView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.toolbar) Toolbar mToolbar;

  private ImageView mAvatorView;
  private TextView mTvName;

  //指示当前的 fragment 是否是findfragment
  private boolean isFindFragment = true;

  private MainComponent mMainComponent;
  @Inject MainPresenter mPresenter;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    context.startActivity(intent);
  }

  @Override protected void initView() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    showFragment(new FindFragment());
    mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
    mToolbar.setTitle("招聘");
    initToolbar(mToolbar);
    navView.setNavigationItemSelectedListener(this);
    View headerLayout = navView.getHeaderView(0);
    mAvatorView = (ImageView) headerLayout.findViewById(R.id.header_view);

    mTvName = (TextView) headerLayout.findViewById(R.id.tv_name);
    mPresenter.attachView(this);
     mAvatorView.setOnClickListener(v -> {
      mPresenter.onAccountClick();
    });
  }

  @Override protected void initInjector() {
    mMainComponent =
        DaggerMainComponent.builder().applicationComponent(getApplicationComponent()).build();
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
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    drawerLayout.closeDrawer(Gravity.LEFT);
    mPresenter.onNavigationItemClick(item);
    return true;
  }

  @Override public void showAccountUi() {
    AccountActivity.startActivity(MainActivity.this);
  }

  @Override public void showLoginUi() {
    LoginActivity.startActivity(this);
  }

  @Override public void updateFindFragment(String key) {

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

  @Override public void setTitle(String title) {
    mToolbar.setTitle(title);
  }

  @Override public void renderAccount(String avatorUrl, String name) {
    Picasso.with(this).load(Uri.parse(avatorUrl)).into(mAvatorView);
    mTvName.setText(name);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }

  @Override public MainComponent getComponent() {
    return mMainComponent;
  }
}
