package com.muxistudio.jobs.ui.main;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.injector.HasComponent;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.ui.accout.AccountActivity;
import com.muxistudio.jobs.ui.find.FindFragment;
import com.muxistudio.jobs.ui.login.LoginActivity;
import com.muxistudio.jobs.ui.setting.SettingFragment;
import com.muxistudio.jobs.util.CircleTransformation;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.util.PreferenceUtil;
import com.muxistudio.jobs.widget.SelectSearchView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;

/**
 * Created by ybao on 16/11/1.
 */

public class MainActivity extends ToolbarActivity
        implements MainContract.View, HasComponent<MainComponent>,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_test)
    TextView mTvTest;
    @Inject
    MainPresenter mPresenter;
    //@BindView(R.id.searchview) SelectSearchView mSearchview;
    private SelectSearchView mSearchView;
    private ImageView mAvatorView;
    private TextView mTvName;
    //指示当前的 fragment 是否是findfragment
    private boolean isFindFragment = true;
    private boolean isSettingFragmentShow = false;
    private MainComponent mMainComponent;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSearchView = (SelectSearchView) findViewById(R.id.search_view);
        Logger.d("is setting show:" + PreferenceUtil.getBoolean(PreferenceUtil.IS_SETTING_SHOW));
        if (PreferenceUtil.getBoolean(PreferenceUtil.IS_SETTING_SHOW)) {
            isSettingFragmentShow = true;
            showSetting();
            mToolbar.setTitle("设置");
        } else {
            showFragment(new FindFragment());
            mToolbar.setTitle("招聘");
        }
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        navView.setNavigationItemSelectedListener(this);
        View headerLayout = navView.getHeaderView(0);
        mAvatorView = (ImageView) headerLayout.findViewById(R.id.header_view);

        mTvName = (TextView) headerLayout.findViewById(R.id.ee);
        mAvatorView.setOnClickListener(v -> {
            mPresenter.onAccountClick();
        });
        isSettingFragmentShow = PreferenceUtil.getBoolean(PreferenceUtil.IS_SETTING_SHOW);
        Logger.d("isSettingFragmentShow" + isSettingFragmentShow);
        if (isSettingFragmentShow) {
            navView.setCheckedItem(R.id.action_setting);
        }
        mPresenter.attachView(this);

        checkPermissionIsGranted();
    }

    @Override
    protected void initInjector() {
        mMainComponent =
                DaggerMainComponent.builder().applicationComponent(
                        getApplicationComponent()).build();
        mMainComponent.inject(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        Logger.d("is findfragmnet? " + isFindFragment);
        if (isFindFragment) {
            getMenuInflater().inflate(R.menu.menu_search, menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
            case R.id.action_search:
                mPresenter.onSearchClick();
                mSearchView.showSearchView();
                mSearchView.setOnSeachViewListener(query -> {
                    ((FindFragment) getSupportFragmentManager().findFragmentById(
                            R.id.content)).loadQuery(
                            query);
                    mPresenter.insertHistory(mSearchView.getEditSearchText());
                    InputMethodManager inputMethodManager =
                            (InputMethodManager) this.getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(
                            getWindow().getDecorView().getWindowToken(), 0);
                });
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        mPresenter.onNavigationItemClick(item);
        return true;
    }

    @Override
    public void showAccountUi() {
        AccountActivity.startActivity(this);
    }

    @Override
    public void showLoginUi() {
        LoginActivity.startActivity(this);
    }

    @Override
    public void showSetting() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, SettingFragment.newInstance(), "setting");
        transaction.addToBackStack(null);
        transaction.commit();
        isFindFragment = false;

        if (isSettingFragmentShow == false) {
            getSupportFragmentManager().beginTransaction()
                    .remove(getSupportFragmentManager().getFragments()
                            .get(getSupportFragmentManager().getFragments().size() - 1))
                    .commit();
            Logger.d("setting sfm:" + getSupportFragmentManager().getBackStackEntryCount());
            Logger.d("setting sfm: size " + getSupportFragmentManager().getFragments().size());
        }

        isSettingFragmentShow = true;
        invalidateOptionsMenu();
    }

    @Override
    public void updateFindFragment(String key) {

    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
        if (fragment instanceof FindFragment) {
            isFindFragment = true;
        } else {
            isFindFragment = false;
        }
        if (isSettingFragmentShow) {
            getFragmentManager().beginTransaction()
                    .remove(getFragmentManager().findFragmentByTag("setting"))
                    .commit();
            PreferenceUtil.putBoolean(PreferenceUtil.IS_SETTING_SHOW, false);
        }
        isSettingFragmentShow = false;
        invalidateOptionsMenu();
    }

    @Override
    public void showSearchView() {

    }

    @DebugLog
    @Override
    public void renderTags(List<String> tagList, List<String> historyList) {
        mSearchView.setSearchTag(tagList);
        mSearchView.setSearchHistory(historyList);
    }

    @Override
    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void renderAccountName(String name) {
        mTvName.setText(name);
    }

    @Override
    public void closeNavView() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void renderAccountAvator(String url) {
        if (TextUtils.isEmpty(url)) {
            Picasso.with(this)
                    .load(R.drawable.default_avatar)
                    .transform(new CircleTransformation())
                    .into(mAvatorView);
            return;
        }
        Picasso.with(this).load(Uri.parse(url)).transform(new CircleTransformation()).into(
                mAvatorView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadUserInfo();
    }

    public void checkPermissionIsGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
                return;
            }
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                        2);
                return;
            }
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isGrandted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isGrandted = false;
                break;
            }
        }
        if (!isGrandted) {
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public MainComponent getComponent() {
        return mMainComponent;
    }
}
