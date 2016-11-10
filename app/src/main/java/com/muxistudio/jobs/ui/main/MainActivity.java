package com.muxistudio.jobs.ui.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.ui.find.FindFragment;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ybao on 16/11/1.
 */

public class MainActivity extends ToolbarActivity implements MainContract.View{

  @BindView(R.id.content) FrameLayout content;
  @BindView(R.id.nav_view) NavigationView navView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

  //指示当前的 fragment 是否是findfragment
  private boolean isFindFragment = true;

  @Inject MainPresenter mPresenter;

  @Override protected void initView() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    showFragment(new FindFragment());
    mPresenter.attachView(this);
  }

  @Override protected void initInjector() {
  //
  }

  @Override protected boolean canBack() {
    return false;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    menu.clear();
    if (isFindFragment) {
      getMenuInflater().inflate(R.menu.menu_search,menu);
    }
    return super.onPrepareOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case android.R.id.home:
        drawerLayout.openDrawer(Gravity.LEFT);
        break;
      case R.id.action_search:
        mPresenter.onSearchClick();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void showAccountUi() {

  }

  @Override public void showFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    Logger.d((fragment instanceof FindFragment) + "");
    if (fragment instanceof FindFragment){
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
}
