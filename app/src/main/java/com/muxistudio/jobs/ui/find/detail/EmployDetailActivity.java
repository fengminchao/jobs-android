package com.muxistudio.jobs.ui.find.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.jobs.JobsApi;
import com.muxistudio.jobs.bean.EmployDetail;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.ui.ToolbarActivity;
import com.muxistudio.jobs.util.TimeUtil;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.view.View.GONE;

/**
 * Created by ybao on 16/11/29.
 */

public class EmployDetailActivity extends ToolbarActivity {

  @Inject JobsApi mJobsApi;
  @Inject CollectionDao mCollectionDao;
  @Inject UserStorge mUserStorge;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.tv_title) TextView mTvTitle;
  @BindView(R.id.tv_venue) TextView mTvVenue;
  @BindView(R.id.tv_time) TextView mTvTime;
  @BindView(R.id.tv_place) TextView mTvPlace;
  @BindView(R.id.tv_content) TextView mTvContent;
  @BindView(R.id.iv_empty) ImageView mIvEmpty;
  @BindView(R.id.scrollView) ScrollView mScrollView;

  private Collection mCollection;

  private int mId;

  @Override protected void initView() {
    setContentView(R.layout.activity_employ_detail);
    initToolbar(mToolbar);
    mToolbar.setTitle("招聘会");
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
        .getEmployDetail(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(employDetail -> {
          hideEmptyView();
          setCollection(employDetail);
          mTvTitle.setText(employDetail.data.title);
          mTvVenue.setText(employDetail.data.venueName);
          mTvPlace.setText(employDetail.data.venueAddress);
          mTvTime.setText(employDetail.data.holdtime);
          mTvContent.setText(employDetail.data.content);
        }, throwable -> {
          throwable.printStackTrace();
          showEmptyView();
        });
  }

  private void setCollection(EmployDetail employDetail) {
    mCollection = new Collection();
    mCollection.setDate(employDetail.data.holdtime);
    mCollection.setMail(mUserStorge.getUser().getMail());
    mCollection.setPlace(employDetail.data.venueAddress);
    mCollection.setSchool("");
    mCollection.setTitle(employDetail.data.title);
    mCollection.setTime(employDetail.data.detailtime.substring());
    mCollection.setType(Constant.TYPE_XJH);
  }

  private void showEmptyView() {
    mIvEmpty.setVisibility(View.VISIBLE);
    mScrollView.setVisibility(GONE);
  }

  private void hideEmptyView() {
    mIvEmpty.setVisibility(GONE);
    mScrollView.setVisibility(View.VISIBLE);
  }

  @Override protected void initInjector() {
    DaggerDetailComponent.builder()
        .applicationComponent(getApplicationComponent())
        .build()
        .inject(this);
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    if (mCollectionDao.queryBuilder().where(CollectionDao.Properties.Id.eq(mId)).list().size()
        > 0) {
      getMenuInflater().inflate(R.menu.info_detail_collected, menu);
    } else {
      getMenuInflater().inflate(R.menu.info_detail, menu);
    }
    return super.onPrepareOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_star:
        if (mCollection != null) {
          mCollectionDao.insert(mCollection);
        }
        break;
      case R.id.action_unstar:
        if (mCollection != null) {
          mCollectionDao.delete(mCollection);
        }
        break;
    }
    invalidateOptionsMenu();
    return true;
  }

}
