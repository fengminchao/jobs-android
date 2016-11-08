package com.muxistudio.jobs.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.ToolbarActivity;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/1.
 */

public class MainActivity extends ToolbarActivity implements MainContract.View{

  @BindView(R.id.content) FrameLayout content;
  @BindView(R.id.nav_view) NavigationView navView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;


  @Override protected void initView() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

  }

  @Override protected void initInjector() {

  }

  @Override protected boolean canBack() {
    return super.canBack();
  }

  public void showFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
  }


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {

    return super.onPrepareOptionsMenu(menu);
  }

  @Override public void showAccountUi() {

  }

  @Override public void showFragment() {

  }

  @Override public void showSearchView() {

  }

  @Override public void renderAccount() {

  }

}
