package com.muxistudio.jobs.ui.register_forget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.ui.login.LoginActivity;
import com.muxistudio.jobs.util.ToastUtil;
import javax.inject.Inject;

/**
 * Created by ybao on 16/10/22.
 * 注册和忘记密码界面,因为两个界面比较一致所以就放在一起了
 */

public class SetPwdActivity extends ToolbarActivity implements SetPwdContract.View {

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.edit_mail) EditText mEditMail;
  @BindView(R.id.layout_name) TextInputLayout mLayoutName;
  @BindView(R.id.edit_pwd) EditText mEditPwd;
  @BindView(R.id.layout_pwd) TextInputLayout mLayoutPwd;
  @BindView(R.id.view_account) LinearLayout mViewAccount;
  @BindView(R.id.et_auth) EditText mEtAuth;
  @BindView(R.id.tv_auth) TextView mTvAuth;

  @Inject SetPwdPresenter mSetPwdPresenter;

  public static final int ACTION_REGISTER = 0;
  public static final int ACTION_FIND = 1;

  //当前 activity 的所属种类
  private int action;

  public static void start(Context context, int action) {
    Intent starter = new Intent(context, SetPwdActivity.class);
    starter.putExtra("action", action);
    context.startActivity(starter);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected void initView() {
    setContentView(R.layout.activity_auth);
    ButterKnife.bind(this);
    action = getIntent().getExtras().getInt("action");
    setTitle(action == ACTION_REGISTER ? "注册" : "找回密码");
    mSetPwdPresenter.attachView(this);
    mTvAuth.setOnClickListener(v -> {
      if (action == ACTION_REGISTER) {
        mSetPwdPresenter.sendAuthCode(mEditMail.getText().toString());
      } else {
        mSetPwdPresenter.sendResetAuthCode(mEditMail.getText().toString());
      }
    });
  }

  @Override protected void initInjector() {
    DaggerSetPwdComponent.builder()
        .applicationComponent(getApplicationComponent())
        .build()
        .inject(this);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_set) {
      if (action == ACTION_REGISTER) {
        mSetPwdPresenter.register(mEditMail.getText().toString(), mEditPwd.getText().toString(),
            mEtAuth.getText().toString());
      } else {
        mSetPwdPresenter.resetPwd(mEditMail.getText().toString(), mEditPwd.getText().toString(),
            mEtAuth.getText().toString());
      }
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.set_pwd,menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public void showMailError(String msg) {
    mLayoutName.setErrorEnabled(true);
    mLayoutName.setError(msg);
  }

  @Override public void showPwdError(String msg) {
    mLayoutPwd.setErrorEnabled(true);
    mLayoutPwd.setError(msg);
  }

  @Override public void showAuthCodeError(String msg) {

  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @Override public void showAuthSend() {
    ToastUtil.toastShort("验证码已发送至邮箱,请注意查收");
  }

  @Override public void showError(String msg) {
    ToastUtil.toastShort(msg);
  }

  @Override public void showLoading() {
    showLoadingDialog("请稍后");
  }

  @Override public void hideLoading() {
    hideLoadingDialog();
  }

  @Override public void authSuccess() {
    LoginActivity.startActivity(this);
    ToastUtil.toastShort("验证成功");
    this.finish();
  }
}
