package com.muxistudio.jobs.ui.accout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.BaseActivity;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

/**
 * Created by ybao on 16/11/12.
 */
@PerActivity public class AccountActivity extends BaseActivity {

  @Inject UserStorge mUserStorge;
  @BindView(R.id.iv_avator) ImageView mIvAvator;
  @BindView(R.id.tv_name) TextView mTvName;
  @BindView(R.id.tv_gender) TextView mTvGender;
  @BindView(R.id.tv_place) TextView mTvPlace;
  @BindView(R.id.tv_birth) TextView mTvBirth;
  @BindView(R.id.tv_politic) TextView mTvPolitic;
  @BindView(R.id.tv_college) TextView mTvCollege;
  @BindView(R.id.tv_phone) TextView mTvPhone;
  @BindView(R.id.tv_mail) TextView mTvMail;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, AccountActivity.class);
    context.startActivity(intent);
  }

  @Override protected void initView() {
    setContentView(R.layout.activity_account);
    ButterKnife.bind(this);
    renderUserInfo();
  }

  private void renderUserInfo() {
    Picasso.with(this).load(Uri.parse(mUserStorge.getUserInfo().getAvator())).into(mIvAvator);
    mTvName.setText(mUserStorge.getUserInfo().getName());
    mTvGender.setText(mUserStorge.getUserInfo().getGender());
    mTvPlace.setText(mUserStorge.getUserInfo().getLive());
    mTvBirth.setText(mUserStorge.getUserInfo().getBirth());
    mTvPolitic.setText(mUserStorge.getUserInfo().getPolitic());
    mTvCollege.setText(mUserStorge.getUserInfo().getCollege());
    mTvPhone.setText(mUserStorge.getUserInfo().getMobile());
    mTvMail.setText(mUserStorge.getUserInfo().getMail());
  }

  @Override protected void initInjector() {
    DaggerAccountComponent.builder()
        .applicationComponent(getApplicationComponent())
        .build()
        .inject(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_edit) {
      AccountEditActivity.startActivity(AccountActivity.this);
    }
    return true;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_account, menu);
    return super.onCreateOptionsMenu(menu);
  }
}
