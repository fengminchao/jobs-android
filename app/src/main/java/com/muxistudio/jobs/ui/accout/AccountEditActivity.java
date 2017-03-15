package com.muxistudio.jobs.ui.accout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.muxistudio.jobs.util.PictureUtil;
import com.muxistudio.jobs.util.TimeUtil;
import com.muxistudio.jobs.util.ToastUtil;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ybao on 16/11/13.
 */

@PerActivity
public class AccountEditActivity extends ToolbarActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_IMAGE_SELECT = 2;
    @Inject
    UserStorge mUserStorge;
    @Inject
    UserInfoDao mUserInfoDao;
    @Inject
    UserApi mUserApi;
    @BindView(R.id.et_name)
    TextInputEditText mEtName;
    @BindView(R.id.name_layout)
    TextInputLayout mNameLayout;
    @BindView(R.id.cb_man)
    AppCompatCheckBox mCbMan;
    @BindView(R.id.cb_woman)
    AppCompatCheckBox mCbWoman;
    @BindView(R.id.et_place)
    TextInputEditText mEtPlace;
    @BindView(R.id.place_layout)
    TextInputLayout mPlaceLayout;
    @BindView(R.id.et_politic)
    TextInputEditText mEtPolitic;
    @BindView(R.id.politc_layout)
    TextInputLayout mPolitcLayout;
    @BindView(R.id.et_college)
    TextInputEditText mEtCollege;
    @BindView(R.id.college_layout)
    TextInputLayout mCollegeLayout;
    @BindView(R.id.et_phone)
    TextInputEditText mEtPhone;
    @BindView(R.id.phone_layout)
    TextInputLayout mPhoneLayout;
    @BindView(R.id.et_mail)
    TextInputEditText mEtMail;
    @BindView(R.id.mail_layout)
    TextInputLayout mMailLayout;
    @BindView(R.id.iv_avator)
    ImageView mIvAvator;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    //暂存的 userinfo
    private UserInfo mUserInfo;
    //头像是否发生改变,改变的话保存时上传头像
    private boolean isAvatorChanged = false;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AccountEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_account_edit);
        ButterKnife.bind(this);
        mToolbar.setTitle("编辑个人信息");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_white_24dp);
        initInputLayout();
        initUserInfo();
        if (!TextUtils.isEmpty(mUserStorge.getUserInfo().getAvator())) {
            Picasso.with(this)
                    .load(mUserStorge.getUserInfo().getAvator())
                    .transform(new CircleTransformation())
                    .into(mIvAvator);
        } else {
            Picasso.with(this)
                    .load(Constant.DEFAULT_AVATOR_URL)
                    .transform(new CircleTransformation())
                    .into(mIvAvator);
        }

        //初始化监听事件
        mIvAvator.setOnClickListener(v -> {
            showSelectDialog();
        });
        mCbMan.setOnCheckedChangeListener((compoundButton, b) -> {
            mCbWoman.setChecked(!b);
        });
        mCbWoman.setOnCheckedChangeListener((compoundButton, b) -> {
            mCbMan.setChecked(!b);
        });
        mTvDate.setOnClickListener(v -> {
            Calendar c = null;
            if (mTvDate.getText().toString().equals("请选择出生日期")) {
                c = Calendar.getInstance();
            } else {
                c = TimeUtil.parseDateToCalendar(mTvDate.getText().toString());
            }
            new DatePickerDialog(AccountEditActivity.this, (datePicker, i, i1, i2) -> {
                mTvDate.setText(i + "-" + (i1 + 1) + "-" + i2);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH) - 1,
                    c.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void initUserInfo() {
        mEtName.setText(mUserStorge.getUserInfo().getName());
        mEtCollege.setText(mUserStorge.getUserInfo().getCollege());
        mEtMail.setText(mUserStorge.getUser().getMail());
        mEtPhone.setText(mUserStorge.getUserInfo().getMobile());
        mEtPlace.setText(mUserStorge.getUserInfo().getLive());
        mEtPolitic.setText(mUserStorge.getUserInfo().getPolitic());
        if (!TextUtils.isEmpty(mUserStorge.getUserInfo().getBirth())) {
            mTvDate.setText(mUserStorge.getUserInfo().getBirth());
        }
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
            } else {
                Picasso.with(this)
                        .load(mUserStorge.getUserInfo().getAvator())
                        .transform(new CircleTransformation())
                        .into(mIvAvator);
            }
        } else {
            Picasso.with(this).load(R.drawable.default_avatar).transform(
                    new CircleTransformation()).into(mIvAvator);
        }
    }

    private void showSelectDialog() {
        new AlertDialog.Builder(this).setTitle("选择头像")
                .setMessage(" ")
                .setNeutralButton("拍照获取", (dialogInterface, i) -> {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                })
                .setPositiveButton("从相册中选取", (dialogInterface, i) -> {
                    //Intent intent = new Intent(Intent.ACTION_PICK);
                    //intent.setType("image/*");
                    //intent.setAction(Intent.ACTION_GET_CONTENT);
                    //startActivityForResult(intent, REQUEST_IMAGE_SELECT);
                    Intent intent =
                            new Intent(Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_IMAGE_SELECT);
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("get acitivity result");
        if (resultCode != RESULT_OK) {
            ToastUtil.showShort("获取失败");
            return;
        }
        isAvatorChanged = true;
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            Logger.d("receive photo from camera");
            Bitmap bitmap;
            try {
                bitmap = (Bitmap) data.getExtras().get("data");
                //Picasso.with(AccountEditActivity.this).load()
                mIvAvator.setImageBitmap(PictureUtil.transToRound(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_IMAGE_SELECT) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null,
                    null);
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(index);
            cursor.close();
            Logger.d("receive photo from picture");
            mIvAvator.setImageBitmap(
                    PictureUtil.transToRound(BitmapFactory.decodeFile(picturePath)));
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

    @Override
    protected void initInjector() {
        DaggerAccountEditComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_confirm) {
            mUserInfo = new UserInfo();
            mUserInfo.setName(mEtName.getText().toString());
            mUserInfo.setGender(getUserGender());
            mUserInfo.setLive(mEtPlace.getText().toString());
            mUserInfo.setCollege(mEtCollege.getText().toString());
            mUserInfo.setMobile(mEtPhone.getText().toString());
            mUserInfo.setMail(mEtMail.getText().toString());
            mUserInfo.setAvator(getAvatorUrl());
            mUserInfo.setBirth(mTvDate.getText().toString());
            //showLoadingDialog("正在保存");
            if (isAvatorChanged) {
                if (TextUtils.isEmpty(mUserStorge.getUserInfo().getAvator())) {
                    uploadAvator("avator/" + mUserStorge.getUser().getMail() + ".jpeg", true);
                } else {
                    uploadAvator("avator/" + mUserStorge.getUser().getMail() + ".jpeg", false);
                }
            } else {
                saveUserInfo();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void uploadAvator(String key, boolean isFirst) {
        Subscription s = mUserApi.getUserService()
                .getUploadToken("avator/" + mUserStorge.getUser().getMail() + ".png",
                        String.valueOf(isFirst))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tokenResultResponse -> {
                    if (tokenResultResponse.code() == 200) {
                        UploadManager uploadManager = new UploadManager();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        Bitmap bitmap = PictureUtil.drawableToBitmap(mIvAvator.getDrawable());
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        byte[] data = bos.toByteArray();
                        uploadManager.put(data, key, tokenResultResponse.body().token,
                                new UpCompletionHandler() {
                                    @Override
                                    public void complete(String key, ResponseInfo info,
                                            JSONObject response) {
                                        if (!response.has("error")) {
                                            mUserInfo.setAvator(Constant.QINIU_URL
                                                    + "avator/"
                                                    + mUserStorge.getUser().getMail()
                                                    + ".jpeg");
                                            PictureUtil.saveAvatorToDisk(
                                                    PictureUtil.drawableToBitmap(
                                                            mIvAvator.getDrawable()),
                                                    mUserStorge.getUser().getMail() + ".jpeg");
                                            saveUserInfo();
                                        } else {
                                            ToastUtil.showShort("保存失败");
                                        }
                                        //hideLoadingDialog();
                                    }
                                }, null);
                    }
                }, throwable -> throwable.printStackTrace());

        mCompositeSubscription.add(s);
    }

    public void saveUserInfo() {
        Subscription s = mUserApi.getUserService()
                .updateUserInfo(mUserInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseData -> {
                    if (baseData.code == 0) {
                        mUserStorge.setUserInfo(mUserInfo);
                        mUserInfoDao.update(mUserStorge.getUserInfo());
                        //hideLoadingDialog();
                        AccountEditActivity.this.finish();
                        ToastUtil.showShort("保存成功");
                    } else {
                        ToastUtil.showShort("保存失败");
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    //hideLoadingDialog();
                });
        mCompositeSubscription.add(s);
    }

    private String getAvatorUrl() {
        if (isAvatorChanged || mUserInfoDao.queryBuilder().where(UserInfoDao.Properties.Mail.eq(
                mUserStorge.getUser().getMail())).build().list().size() > 0) {
            return "http://ognz3v7yx.bkt.clouddn.com/avator/" + mUserStorge.getUser().getMail()
                    + ".jpeg";
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
