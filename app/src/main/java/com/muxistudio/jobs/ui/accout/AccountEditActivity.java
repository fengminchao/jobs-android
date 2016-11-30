package com.muxistudio.jobs.ui.accout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
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
import com.muxistudio.jobs.util.CircleTransformation;
import com.muxistudio.jobs.util.FileUtil;
import com.muxistudio.jobs.util.Logger;
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

  @BindView(R.id.et_name) TextInputEditText mEtName;
  @BindView(R.id.name_layout) TextInputLayout mNameLayout;
  @BindView(R.id.cb_man) AppCompatCheckBox mCbMan;
  @BindView(R.id.cb_woman) AppCompatCheckBox mCbWoman;
  @BindView(R.id.et_place) TextInputEditText mEtPlace;
  @BindView(R.id.place_layout) TextInputLayout mPlaceLayout;
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
  @BindView(R.id.tv_date) TextView mTvDate;

  private CompositeSubscription mCompositeSubscription;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, AccountEditActivity.class);
    context.startActivity(intent);
  }

  @Override protected void initView() {
    setContentView(R.layout.activity_account_edit);
    ButterKnife.bind(this);
    mToolbar.setTitle("编辑个人信息");
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_white_24dp);
    initInputLayout();
    initUserInfo();
    if (!TextUtils.isEmpty(mUserStorge.getUserInfo().getAvator())) {
      Picasso.with(this).load(mUserStorge.getUserInfo().getAvator()).into(mIvAvator);
    } else {
      Picasso.with(this).load(Constant.DEFAULT_AVATOR_URL).into(mIvAvator);
    }
    mIvAvator.setOnClickListener(v -> {
      showSelectDialog();
    });
    mCbMan.setOnClickListener(v -> {
      mCbWoman.setChecked(mCbMan.isChecked());
      mCbMan.setChecked(!mCbMan.isChecked());
    });
    mCbWoman.setOnClickListener(v -> {
      mCbMan.setChecked(mCbWoman.isChecked());
      mCbWoman.setChecked(!mCbWoman.isChecked());
    });
  }

  private void initUserInfo() {
    mEtName.setText(mUserStorge.getUserInfo().getName());
    mEtCollege.setText(mUserStorge.getUserInfo().getCollege());
    mEtMail.setText(mUserStorge.getUserInfo().getMail());
    mEtPhone.setText(mUserStorge.getUserInfo().getMobile());
    mEtPlace.setText(mUserStorge.getUserInfo().getLive());
    mEtPolitic.setText(mUserStorge.getUserInfo().getPolitic());
    mTvDate.setText(mUserStorge.getUserInfo().getBirth());
    if (!TextUtils.isEmpty(mUserStorge.getUserInfo().getGender())) {
      if (mUserStorge.getUserInfo().getGender().equals("男")) {
        mCbMan.setChecked(true);
      } else {
        mCbWoman.setChecked(true);
      }
    } else {
      mCbMan.setChecked(true);
    }
    if (!TextUtils.isEmpty(mUserStorge.getUserInfo().getAvator())) {
      if (FileUtil.getAvatorFromDisk(mUserStorge.getUser().getMail()).length() > 0) {
        Picasso.with(this)
            .load(FileUtil.getAvatorFromDisk(mUserStorge.getUser().getMail()))
            .transform(new CircleTransformation())
            .into(mIvAvator);
      }else {
        Picasso.with(this)
            .load(mUserStorge.getUserInfo().getAvator())
            .transform(new CircleTransformation())
            .into(mIvAvator);
      }
    }else {
      Picasso.with(this)
          .load(R.drawable.cat)
          .transform(new CircleTransformation())
          .into(mIvAvator);
    }
  }


  public static final int REQUEST_IMAGE_CAPTURE = 1;
  public static final int REQUEST_IMAGE_SELECT = 2;

  private void showSelectDialog() {
    new AlertDialog.Builder(this).setTitle("选择头像")
        .setMessage(" ")
        .setNeutralButton("拍照获取", ((dialogInterface, i) -> {
          Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
          startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }))
        .setPositiveButton("从相册中选取", ((dialogInterface, i) -> {
          //Intent intent = new Intent(Intent.ACTION_PICK);
          //intent.setType("image/*");
          //intent.setAction(Intent.ACTION_GET_CONTENT);
          //startActivityForResult(intent, REQUEST_IMAGE_SELECT);
          Intent intent =
              new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          startActivityForResult(intent, REQUEST_IMAGE_SELECT);
        }))
        .show();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Logger.d("get acitivity result");
    if (resultCode != RESULT_OK) {
      ToastUtil.toastShort("获取失败");
      return;
    }
    if (requestCode == REQUEST_IMAGE_CAPTURE) {
      Logger.d("receive photo from camera");
      Bitmap bitmap;
      try {
        bitmap = (Bitmap) data.getExtras().get("data");
        //Picasso.with(AccountEditActivity.this).load()
        mIvAvator.setImageBitmap(bitmap);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (requestCode == REQUEST_IMAGE_SELECT) {
      Uri selectedImage = data.getData();
      String[] filePathColumn = { MediaStore.Images.Media.DATA };
      Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
      cursor.moveToFirst();
      int index = cursor.getColumnIndex(filePathColumn[0]);
      String picturePath = cursor.getString(index);
      cursor.close();
      Logger.d("receive photo from picture");
      mIvAvator.setImageBitmap(BitmapFactory.decodeFile(picturePath));
    }
  }

  private void initInputLayout() {
    mMailLayout.setHint(getString(R.string.account_mail));
    mPhoneLayout.setHint(getString(R.string.account_mobile));
    mNameLayout.setHint(getString(R.string.account_name));
    mCollegeLayout.setHint(getString(R.string.account_college));
    mPlaceLayout.setHint(getString(R.string.account_live));
    mPolitcLayout.setHint(getString(R.string.account_politic));
  }

  @Override protected void initInjector() {
    DaggerAccountEditComponent.builder()
        .applicationComponent(getApplicationComponent())
        .build()
        .inject(this);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_account_edit, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_confirm) {
      UserInfo userInfo = new UserInfo();
      userInfo.setName(mEtName.getText().toString());
      userInfo.setGender(getUserGender());
      userInfo.setLive(mEtPlace.getText().toString());
      userInfo.setCollege(mEtCollege.getText().toString());
      userInfo.setMobile(mEtPhone.getText().toString());
      userInfo.setMail(mEtMail.getText().toString());
      userInfo.setAvator(getAvatorUrl());
      userInfo.setBirth(mTvDate.getText().toString());


      mUserApi.getUserService().updateUserInfo(userInfo).subscribe(baseData -> {
        mUserStorge.setUserInfo(userInfo);
        mUserInfoDao.update(userInfo);
        ToastUtil.toastShort("保存信息成功");
        onBackPressed();
      }, throwable -> {
        throwable.printStackTrace();
        ToastUtil.toastShort("保存信息失败");
      });
    }
    return super.onOptionsItemSelected(item);
  }

  private String getAvatorUrl() {
    return "";
  }

  public String getUserGender() {
    if (mCbMan.isChecked()) {
      return "男";
    }
    if (mCbWoman.isChecked()) {
      return "女";
    }
    return "";
  }
}
