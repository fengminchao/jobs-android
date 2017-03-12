package com.muxistudio.jobs.ui.find.detail;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.jobs.JobsApi;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.bean.CareerDetail;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.util.TimeUtil;
import com.muxistudio.jobs.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/11/29.
 */

@PerActivity
public class CareerDetailActivity extends ToolbarActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_college)
    TextView mTvCollege;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_place)
    TextView mTvPlace;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.iv_empty)
    ImageView mIvEmpty;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @Inject
    JobsApi mJobsApi;
    @Inject
    UserApi mUserApi;
    @Inject
    UserStorge mUserStorge;
    @Inject
    CollectionDao mCollectionDao;
    private Collection mCollection;
    private int mId;

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, CareerDetailActivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_career_detail);
        ButterKnife.bind(this);
        initToolbar();
        setTitle(getString(R.string.find_career));
        if (getIntent().hasExtra("id")) {
            loadDetailData(getIntent().getIntExtra("id", -1));
            mId = getIntent().getIntExtra("id", -1);
        }
    }

    private void loadDetailData(int id) {
        if (id == -1) {
            return;
        }
        mJobsApi.getJobsService()
                .getCareerDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(careerDetail -> {
                    hideEmptyView();
                    setCollection(careerDetail);
                    mTvTitle.setText(careerDetail.data.title);
                    mTvCollege.setText(careerDetail.data.universityName);
                    mTvPlace.setText(careerDetail.data.address);
                    mTvTime.setText(careerDetail.data.holdtime);
                    mTvContent.setText(Html.fromHtml(careerDetail.data.content));
                }, throwable -> {
                    throwable.printStackTrace();
                    showEmptyView();
                });
    }

    private void setCollection(CareerDetail careerDetail) {
        mCollection = new Collection();
        mCollection.setDate(TimeUtil.parseDate(careerDetail.data.holdtime));
        mCollection.setMail(mUserStorge.getUser().getMail());
        mCollection.setPlace(careerDetail.data.address);
        mCollection.setSchool(careerDetail.data.universityName);
        mCollection.setTitle(careerDetail.data.title);
        mCollection.setTime(TimeUtil.parseTime(careerDetail.data.holdtime));
        mCollection.setType(getString(R.string.find_career));
        mCollection.setId(careerDetail.data.id);
    }

    private void showEmptyView() {
        mIvEmpty.setVisibility(View.VISIBLE);
        mScrollView.setVisibility(GONE);
    }

    private void hideEmptyView() {
        mIvEmpty.setVisibility(GONE);
        mScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initInjector() {
        DaggerDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mCollectionDao.queryBuilder().where(CollectionDao.Properties.Id.eq(mId)).list().size()
                > 0) {
            getMenuInflater().inflate(R.menu.info_detail_collected, menu);
            Logger.d("collected menu inflate");
        } else {
            getMenuInflater().inflate(R.menu.info_detail, menu);
            Logger.d("collect menu inflate");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_star:
                if (mCollection != null) {
                    mCollectionDao.insert(mCollection);
                }
                mUserApi.getUserService().addCollection(mCollection)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseData -> {
                            if (baseData.code == 0) {
                                //ToastUtil.showShort(getString(R.string.save_success));
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
                            ToastUtil.showShort(getString(R.string.err_net));
                        });
                break;
            case R.id.action_unstar:
                if (mCollection != null) {
                    mCollectionDao.delete(mCollection);
                }
                mUserApi.getUserService().removeCollection(mCollection.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseData -> {
                            if (baseData.code == 0) {
                                ToastUtil.showShort(getString(R.string.not_save_success));
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
                            ToastUtil.showShort(getString(R.string.err_net));
                        });
                break;
        }
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }
}
