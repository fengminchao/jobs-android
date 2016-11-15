package com.muxistudio.jobs.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ybao on 16/10/19.
 */
public class LoginActivity extends ToolbarActivity implements LoginContract.View {

  @Inject LoginPresenter mPresenter;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.edit_name) EditText mEditName;
  @BindView(R.id.layout_name) TextInputLayout mLayoutName;
  @BindView(R.id.edit_pwd) EditText mEditPwd;
  @BindView(R.id.layout_pwd) TextInputLayout mLayoutPwd;
  @BindView(R.id.btn_forget) Button mBtnForget;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, LoginActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected void initInjector() {
    DaggerLoginComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build()
        .inject(this);
  }

  @Override protected void initView() {
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    mPresenter.attachView(this);
    initToolbar(mToolbar);
    setTitle("登录");
  }

  @Override public void showError(String err) {
    ToastUtil.toastShort(err);
  }

  @Override public void showLoading() {
    showLoadingDialog("登陆中");
  }

  @Override public void hideLoading() {
    hideLoadingDialog();
  }

  @Override public void showMainActivity() {
            MainActivity.startActivity(this);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_login, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_login) {
      doLogin();
    }
    return super.onOptionsItemSelected(item);
  }

  private void doLogin() {
    String username = mEditName.getText().toString();
    String pwd = mEditPwd.getText().toString();
    mPresenter.login(username, pwd);
  }
}
