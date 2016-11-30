package com.muxistudio.jobs.ui.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.injector.components.ApplicationComponent;
import com.muxistudio.jobs.injector.modules.ActivityModule;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.util.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.util.ToastUtil;

/**
 * Created by ybao on 16/10/16.
 */

public class SettingActiviy extends ToolbarActivity {

  @BindView(R.id.image_view) ImageView mImageView;
  @BindView(R.id.button) Button mButton;
  @BindView(R.id.button2) Button mButton2;
  @BindView(R.id.editText) EditText mEditText;
  @BindView(R.id.textView) TextView mTextView;
  @BindView(R.id.textView2) TextView mTextView2;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected void initView() {
    setContentView(R.layout.activity_setting);
    ButterKnife.bind(this);
    mButton.setOnClickListener(v -> {
      PreferenceUtil.putBoolean(PreferenceUtil.IS_NIGHT_THEME, false);
      ToastUtil.toastShort("btn2aff");
      Logger.d("btn1");
      reload();
    });
    mButton2.setText("btn2");
    mButton2.setOnClickListener(v -> {
      PreferenceUtil.putBoolean(PreferenceUtil.IS_NIGHT_THEME, true);
      ToastUtil.toastShort("btn2");
      Logger.d("btn2");
      reload();
    });
  }

  @Override protected void initInjector() {

  }

  @Override public ApplicationComponent getApplicationComponent() {
    return super.getApplicationComponent();
  }

  @Override protected ActivityModule getActivityModule() {
    return super.getActivityModule();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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
  }

  @Override protected void showLoadingDialog(String msg) {
    super.showLoadingDialog(msg);
  }

}
