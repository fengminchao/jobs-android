package com.muxistudio.jobs.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.BuildConfig;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.injector.modules.ActivityModule;
import com.muxistudio.jobs.util.PreferenceUtil;
import java.util.Random;

/**
 * Created by ybao on 16/10/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

  private ProgressDialog mProgressDialog;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    getApplicationComponent().inject(this);
    initTheme();
    super.onCreate(savedInstanceState);
    initInjector();
    initView();
    mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setCancelable(true);
    //AppManager.getAppManager().addActivity(this);
  }

  public void initTheme() {
    int theme;
    try {
      theme = getPackageManager().getActivityInfo(getComponentName(), 0).theme;
    } catch (PackageManager.NameNotFoundException e) {
      return;
    }
    if (theme != R.style.AppThemeSplash) {
      if (PreferenceUtil.getBoolean(PreferenceUtil.IS_NIGHT_THEME)){
        theme = R.style.AppThemeDark;
      }else {
        theme = R.style.AppThemeLight;
      }
      //theme = PreferenceUtil.getBoolean(PreferenceUtil.IS_NIGHT_THEME) ? R.style.AppThemeDark
      //    : R.style.AppThemeLight;
    }

    setTheme(theme);
  }

  protected abstract void initView();

  protected abstract void initInjector();

  public ApplicationComponent getApplicationComponent() {
    return ((App) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    //AppManager.getAppManager().finishActivity(this);
  }

  protected void showLoadingDialog(String msg) {
    if (!mProgressDialog.isShowing()) {
      mProgressDialog.show(this, null, msg);
    }
  }

  protected void hideLoadingDialog() {
    if (mProgressDialog.isShowing()) {
      mProgressDialog.hide();
    }
  }

  public void reload() {
    Intent intent = getIntent();
    overridePendingTransition(0, 0);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    finish();
    overridePendingTransition(0, 0);
    startActivity(intent);
  }


}
