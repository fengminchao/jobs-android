package com.muxistudio.jobs.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MenuItem;

import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.History;
import com.muxistudio.jobs.db.HistoryDao;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.SubscriptionPresenter;
import com.muxistudio.jobs.ui.about.AboutFragment;
import com.muxistudio.jobs.ui.collection.CollectionFragment;
import com.muxistudio.jobs.ui.find.FindFragment;
import com.muxistudio.jobs.ui.schedule.ScheduleFragment;
import com.muxistudio.jobs.util.Logger;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/11/7.
 */

@PerActivity
public class MainPresenter extends SubscriptionPresenter
        implements MainContract.Presenter {

    private UserDao mUserDao;
    private Context mContext;
    private UserStorge mUserStorge;
    private HistoryDao mHistoryDao;
    private UserApi mUserApi;

    private List<String> mTagList;
    private List<String> mHistoryList;

    private MainContract.View mMainView;

    @Inject
    public MainPresenter(UserStorge userStorge, UserDao userDao, Context context,
            HistoryDao historyDao,UserApi userApi) {
        mUserStorge = userStorge;
        mUserDao = userDao;
        mContext = context;
        mHistoryDao = historyDao;
        mUserApi = userApi;
    }

    @Override
    public void onAccountClick() {
        Logger.d(mMainView == null ? "mainview is null" : "mainview not null");
        mMainView.closeNavView();
        if (mUserStorge.isLogin()) {
            mMainView.showAccountUi();
            return;
        }
        mMainView.showLoginUi();
    }

    @Override
    public void onSearchClick() {
        //if (mTagList != null && !mTagList.isEmpty() && mHistoryList != null && !mHistoryList
      // .isEmpty()){

        //}
        if (mTagList == null) {
            mTagList = new ArrayList<>();
            if (mUserStorge.getUserInfo() != null) {
                if (!TextUtils.isEmpty(mUserStorge.getUserInfo().getLive())) {
                    mTagList.add(mUserStorge.getUserInfo().getLive());
                }
                if (!TextUtils.isEmpty(mUserStorge.getUserInfo().getCollege())) {
                    mTagList.add(mUserStorge.getUserInfo().getCollege());
                }
            }
            mTagList.addAll(Arrays.asList(Constant.TAG_ARRAY));
        }
        if (mHistoryList == null) {
            mHistoryList = new ArrayList<>();
            Query historyQuery = mHistoryDao.queryBuilder()
                    .where(HistoryDao.Properties.Mail.eq(mUserStorge.getUser().getMail()))
                    .build();
            if (historyQuery != null) {
                List<History> historyList = historyQuery.list();
                for (History history : historyList) {
                    mHistoryList.add(history.getQuery());
                }
            }
        }
        mMainView.renderTags(mTagList, mHistoryList);
    }

    @Override
    public void onNavigationItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_find:
                mMainView.showFragment(FindFragment.newInstance());
                break;
            case R.id.action_date:
                mMainView.showFragment(ScheduleFragment.newInstance());
                break;
            case R.id.action_person:
                mMainView.showFragment(CollectionFragment.newInstance());
                break;
            case R.id.action_discuss:
                break;
            case R.id.action_setting:
                //mMainView.showFragment(SettingFragment.newInstance());
                mMainView.showSetting();
                break;
            case R.id.action_about:
                mMainView.showFragment(AboutFragment.newInstance());
                break;
        }
        mMainView.setTitle(item.getTitle().toString());
    }

    @Override
    public void exist() {

    }

    @Override
    public void attachView(@NonNull MainContract.View view) {
        mMainView = view;
        updateToken();
        initUserInfo();
    }

    private void updateToken() {
        mUserApi.getUserService().login(mUserStorge.getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tokenResult -> {
                    mUserStorge.setToken(tokenResult.token);
                },throwable -> throwable.printStackTrace());
    }

    @Override
    public void loadUserInfo() {
        this.initUserInfo();
    }

    private void initUserInfo() {
        Logger.d(mUserStorge.isLogin() + "");
        Logger.d(mUserStorge.getUserInfo().getAvator());
        if (mUserStorge.isLogin()) {
            String url = mUserStorge.getUserInfo().getAvator();
            String name = mUserStorge.getUserInfo().getName();
            mMainView.renderAccountAvator(url);
            if (TextUtils.isEmpty(name)) {
                mMainView.renderAccountName(mUserStorge.getUser().getMail());
            } else {
                mMainView.renderAccountName(mUserStorge.getUserInfo().getName());
            }
        } else {
            mMainView.renderAccountAvator("");
            mMainView.renderAccountName("未登录");
        }
    }

    @DebugLog
    @Override
    public void insertHistory(String query) {
        History history = new History(null, query, mUserStorge.getUser().getMail());
        mHistoryDao.insert(history);
        mHistoryList.add(query);
    }

    @Override
    public void detachView() {
        super.detachView();
        mMainView = null;
    }
}
