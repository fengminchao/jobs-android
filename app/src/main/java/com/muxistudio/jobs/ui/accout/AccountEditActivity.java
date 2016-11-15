package com.muxistudio.jobs.ui.accout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.UserInfo;
import com.muxistudio.jobs.db.UserInfoDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.util.ToastUtil;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ybao on 16/11/13.
 */

@PerActivity public class AccountEditActivity extends ToolbarActivity {

  @Inject UserStorge mUserStorge;
  @Inject UserInfoDao mUserInfoDao;
  @Inject UserApi mUserApi;

  @BindView(R.id.tv_change_avator) TextView mTvChangeAvator;
  @BindView(R.id.et_name) TextInputEditText mEtName;
  @BindView(R.id.name_layout) TextInputLayout mNameLayout;
  @BindView(R.id.cb_man) AppCompatCheckBox mCbMan;
  @BindView(R.id.cb_woman) AppCompatCheckBox mCbWoman;
  @BindView(R.id.et_place) TextInputEditText mEtPlace;
  @BindView(R.id.place_layout) TextInputLayout mPlaceLayout;
  @BindView(R.id.et_birth) TextInputEditText mEtBirth;
  @BindView(R.id.birth_layout) TextInputLayout mBirthLayout;
  @BindView(R.id.et_politic) TextInputEditText mEtPolitic;
  @BindView(R.id.politc_layout) TextInputLayout mPolitcLayout;
  @BindView(R.id.et_college) TextInputEditText mEtCollege;
  @BindView(R.id.college_layout) TextInputLayout mCollegeLayout;
  @BindView(R.id.et_phone) TextInputEditText mEtPhone;
  @BindView(R.id.phone_layout) TextInputLayout mPhoneLayout;
  @BindView(R.id.et_mail) TextInputEditText mEtMail;
  @BindView(R.id.mail_layout) TextInputLayout mMailLayout;
  @BindView(R.id.iv_avator) ImageView mIvAvator;
  @BindView(R.id.toolbar) Toolbar mToolbar;

  private CompositeSubscription mCompositeSubscription;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, AccountEditActivity.class);
    context.startActivity(intent);
  }

  @Override protected void initView() {
    setContentView(R.layout.activity_account_edit);
    ButterKnife.bind(this);
    mToolbar.setTitle("");
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_white_24dp);
    initInputLayout();
    if (TextUtils.isEmpty(mUserStorge.getUserInfo().getAvator())) {
      Picasso.with(this).load(mUserStorge.getUserInfo().getAvator()).into(mIvAvator);
    }else {
      Picasso.with(this).load(Constant.DEFAULT_AVATOR_URL).into(mIvAvator);
    }
  }

  private void initInputLayout() {
    mBirthLayout.setHint(getString(R.string.account_birth));
    mMailLayout.setHint(getString(R.string.account_mail));
    mPhoneLayout.setHint(getString(R.string.account_mobile));
    mNameLayout.setHint(getString(R.string.account_name));
    mCollegeLayout.setHint(getString(R.string.account_college));
    mPlaceLayout.setHint(getString(R.string.account_live));
    mPolitcLayout.setHint(getString(R.string.account_politic));
  }

  @Override protected void initInjector() {
    DaggerAccountEditComponent.builder()
        .build()
        .inject(this);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_account_edit,menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_confirm){
      UserInfo userInfo = new UserInfo();
      userInfo.setName(mEtName.getText().toString());
      userInfo.setGender(getUserGender());
      userInfo.setLive(mEtPlace.getText().toString());
      userInfo.setBirth(mEtBirth.getText().toString());
      userInfo.setPolitic(mEtBirth.getText().toString());
      userInfo.setCollege(mEtCollege.getText().toString());
      userInfo.setMobile(mEtPhone.getText().toString());
      userInfo.setMail(mEtMail.getText().toString());
      userInfo.setAvator(getAvatorUrl());

      mUserApi.getUserService().updateUserInfo(userInfo)
          .subscribe(baseData -> {
            mUserStorge.setUserInfo(userInfo);
            mUserInfoDao.update(userInfo);
            ToastUtil.toastShort("保存信息成功");
            onBackPressed();
          },throwable -> {
            throwable.printStackTrace();
            ToastUtil.toastShort("保存信息失败");
          });
    }
    return super.onOptionsItemSelected(item);
  }

  private String getAvatorUrl() {
    return "";
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }

  public String getUserGender(){
    if (mCbMan.isChecked()){
      return "男";
    }
    return "女";
  }
}
