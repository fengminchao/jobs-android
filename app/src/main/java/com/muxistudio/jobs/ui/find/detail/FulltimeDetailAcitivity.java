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
import com.muxistudio.jobs.bean.FulltimeDetail;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.util.CollectionsUtil;
import com.muxistudio.jobs.util.PreferenceUtil;
import com.muxistudio.jobs.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/11/29.
 */

public class FulltimeDetailAcitivity extends ToolbarActivity {

    @Inject
    JobsApi mJobsApi;
    @Inject
    CollectionDao mCollectionDao;
    @Inject
    UserStorge mUserStorge;
    @Inject
    UserApi mUserApi;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_empty)
    ImageView mIvEmpty;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_company)
    TextView mTvCompany;
    @BindView(R.id.tv_place)
    TextView mTvPlace;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    private Collection mCollection;
    private String submitUrl;
    private int mId;

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, FulltimeDetailAcitivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_fulltime_detail);
        ButterKnife.bind(this);
        initToolbar();
        setTitle(getString(R.string.find_fulltime));
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
                .getFulltimeDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fulltimeDetail -> {
                    hideEmptyView();
                    setCollection(fulltimeDetail);
                    mTvTitle.setText(fulltimeDetail.data.title);
                    mTvCompany.setText(fulltimeDetail.data.companyFullname);
                    mTvPlace.setText(fulltimeDetail.data.companyLocationName);
                    mTvType.setText(fulltimeDetail.data.companyTradeTypeName);
                    mTvContent.setText(Html.fromHtml(fulltimeDetail.data.content));
                    submitUrl = fulltimeDetail.data.positions.get(0).postUrl;
                }, throwable -> {
                    throwable.printStackTrace();
                    showEmptyView();
                });
    }

    private void setCollection(FulltimeDetail fulltimeDetail) {
        mCollection = new Collection();
        mCollection.setDate("");
        mCollection.setTime("");
        mCollection.setMail(mUserStorge.getUser().getMail());
        mCollection.setPlace(fulltimeDetail.data.companyLocationName);
        mCollection.setSchool("");
        mCollection.setTitle(fulltimeDetail.data.title);
        mCollection.setType(getString(R.string.find_fulltime));
        mCollection.setId((long) mId);
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
        if (PreferenceUtil.getString(PreferenceUtil.COLLECTION_IDS).contains(String.valueOf(mId))) {
            getMenuInflater().inflate(R.menu.info_detail_collected, menu);
        } else {
            getMenuInflater().inflate(R.menu.info_detail, menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_star:
                CollectionsUtil.addCollectionId(String.valueOf(mId));
                mUserApi.getUserService().addCollection(mCollection)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseData -> {
                            if (baseData.code == 0) {
                                ToastUtil.showShort(getString(R.string.save_success));
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
                            ToastUtil.showShort(getString(R.string.err_net));
                        });
                break;
            case R.id.action_unstar:
                CollectionsUtil.removeCollectionId(String.valueOf(mId));
                mUserApi.getUserService().removeCollection(mId)
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
