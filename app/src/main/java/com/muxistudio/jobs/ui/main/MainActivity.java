package com.muxistudio.jobs.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.injector.HasComponent;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.ui.accout.AccountActivity;
import com.muxistudio.jobs.ui.find.FindFragment;
import com.muxistudio.jobs.ui.login.LoginActivity;
import com.muxistudio.jobs.ui.setting.SettingActiviy;
import com.muxistudio.jobs.util.CircleTransformation;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.widget.SelectSearchView;
import com.squareup.picasso.Picasso;
import hugo.weaving.DebugLog;
import java.util.ArrayList;
import java.util.List;
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
  @BindView(R.id.tv_test) TextView mTvTest;
  //@BindView(R.id.searchview) SelectSearchView mSearchview;
  private SelectSearchView mSearchView;

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
    mSearchView = (SelectSearchView) findViewById(R.id.search_view);
    Logger.d(mSearchView == null ? "searchview is null" : "searchview is not null");
    Logger.d(mTvTest == null ? "tvtest is null" : "tvtest is not null");
    showFragment(new FindFragment());
    mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
    mToolbar.setTitle("招聘");
    navView.setNavigationItemSelectedListener(this);
    View headerLayout = navView.getHeaderView(0);
    mAvatorView = (ImageView) headerLayout.findViewById(R.id.header_view);

    mTvName = (TextView) headerLayout.findViewById(R.id.tv_name);
    mAvatorView.setOnClickListener(v -> {
      mPresenter.onAccountClick();
    });
    mPresenter.attachView(this);
    checkPermissionIsGranted();
    //if (isAppPermissionGranted()){
    //  Logger.d("permission has granted");
    //}

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
        mSearchView.showSearchView();
        //List<String> list = new ArrayList<>();
        //list.add("tag1");
        //list.add("tag2");
        //for (int i = 0; i < 20; i++) {
        //  list.add("tag" + i);
        //}
        //mSearchView.setSearchTag(list);
        // load query text in FindFragment
        mSearchView.setOnSeachViewListener(query -> {
          ((FindFragment) getSupportFragmentManager().findFragmentById(R.id.content)).loadQuery(
              query);
          mPresenter.insertHistory(mSearchView.getEditSearchText());
        });
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
    AccountActivity.startActivity(this);
  }

  @Override public void showLoginUi() {
    LoginActivity.startActivity(this);
  }

  @Override public void showSetting() {
    SettingActiviy.start(this);
  }

  @Override public void updateFindFragment(String key) {

  }

  @Override public void showFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    //getSupportFragmentManager().beginTransaction().addToBackStack(null);
    Logger.d((fragment instanceof FindFragment) + "");
    if (fragment instanceof FindFragment) {
      isFindFragment = true;
    } else {
      isFindFragment = false;
    }
    invalidateOptionsMenu();
  }

  @Override public void showSearchView() {

  }

  @DebugLog
  @Override public void renderTags(List<String> tagList, List<String> historyList) {
    mSearchView.setSearchTag(tagList);
    mSearchView.setSearchHistory(historyList);
  }

  @Override public void setTitle(String title) {
    mToolbar.setTitle(title);
  }

  @Override public void renderAccountName(String name) {
    mTvName.setText(name);
  }

  @Override public void closeNavView() {
    drawerLayout.closeDrawer(Gravity.LEFT);
  }

  @Override public void renderAccountAvator(String url) {
    if (TextUtils.isEmpty(url)) {
      Picasso.with(this)
          .load(R.drawable.cat)
          .transform(new CircleTransformation())
          .into(mAvatorView);
      return;
    }
    Picasso.with(this).load(Uri.parse(url)).transform(new CircleTransformation()).into(mAvatorView);
  }

  @Override protected void onResume() {
    super.onResume();
    mPresenter.loadUserInfo();
  }

  public void checkPermissionIsGranted() {
    if (Build.VERSION.SDK_INT >= 23) {
      if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
          && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
          != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,
            new String[] { Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE },
            1);
        return;
      }
      if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, 2);
        return;
      }
      if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
          != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,
            new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 3);
        return;
      }
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    boolean isGrandted = true;
    for (int result : grantResults) {
      if (result != PackageManager.PERMISSION_GRANTED) {
        isGrandted = false;
        break;
      }
    }
    if (!isGrandted) {
      this.finish();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }

  @Override public MainComponent getComponent() {
    return mMainComponent;
  }
}
