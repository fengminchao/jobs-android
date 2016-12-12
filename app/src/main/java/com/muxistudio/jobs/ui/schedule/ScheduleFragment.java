package com.muxistudio.jobs.ui.schedule;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.muxistudio.jobs.App;
import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.bean.CollectionData;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.collection.CollectionAdapter;
import com.muxistudio.jobs.ui.find.detail.CareerDetailActivity;
import com.muxistudio.jobs.ui.find.detail.EmployDetailActivity;
import com.muxistudio.jobs.ui.find.detail.FulltimeDetailAcitivity;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.util.TimeUtil;
import com.muxistudio.jobs.util.TypeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import org.greenrobot.greendao.test.DbTest;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/12/8.
 */

@PerActivity public class ScheduleFragment extends BaseFragment {

  @BindView(R.id.compactcalendar_view) CompactCalendarView mCompactcalendarView;
  @BindView(R.id.tv_title) TextView mTvTitle;
  @BindView(R.id.rv) RecyclerView mRv;

  private CollectionAdapter mAdapter;

  private List<Collection> mCollections;
  private List<Event> mEvents;
  @Inject UserStorge mUserStorge;
  @Inject CollectionDao mCollectionDao;
  @Inject UserApi mUserApi;

  public static ScheduleFragment newInstance() {
     ScheduleFragment fragment = new ScheduleFragment();
    return fragment;
  }

  @Override public void initInjector() {
    ((MainActivity) getActivity()).getComponent().inject(this);
  }

  @Override public int loadContentView() {
    return R.layout.fragment_schedule;
  }

  @Override protected void initView(View view) {
    ButterKnife.bind(this, view);
    loadCollection();
    initCalendar();
    mRv.setHasFixedSize(true);
    mRv.setLayoutManager(new LinearLayoutManager(getContext()));
  }

  private void loadCollection() {
    mCollections = mCollectionDao.queryBuilder()
        .where(CollectionDao.Properties.Mail.eq(mUserStorge.getUser().getMail()))
        .build()
        .list();
    Logger.d(mCollections != null ? mCollections.size() + "" : "collection is null");
  }

  private void initCalendar() {
    mCompactcalendarView.setLocale(TimeZone.getTimeZone("GMT+08:00"), Locale.CHINA);
    mCompactcalendarView.setUseThreeLetterAbbreviation(true);
    Date date = new Date(System.currentTimeMillis());
    mCompactcalendarView.setCurrentDate(date);
    addEvent();

    mCompactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

      @Override public void onDayClick(Date dateClicked) {
        if (mCompactcalendarView.getEvents(dateClicked) != null
            && mCompactcalendarView.getEvents(dateClicked).size() > 0) {
          renderRecyclerView(dateClicked);
        } else {
          hideRecyclerView();
        }
      }

      @Override public void onMonthScroll(Date firstDayOfNewMonth) {

        ((MainActivity)getActivity()).setTitle(firstDayOfNewMonth.getMonth() + "月");

        if (mCompactcalendarView.getEvents(firstDayOfNewMonth) != null
            && mCompactcalendarView.getEvents(firstDayOfNewMonth).size() > 0) {
          renderRecyclerView(firstDayOfNewMonth);
        } else {
          hideRecyclerView();
        }
      }
    });
  }

  public void addEvent() {
    mEvents = new ArrayList<>();
    Observable.from(mCollections)
        .subscribeOn(Schedulers.io())
        .map(mCollections -> mCollections.getDate())
        .filter(s -> !TextUtils.isEmpty(s))
        .map(s -> {
          Date date = TimeUtil.parseDateStrToDate(s);
          return date.getTime();
        })
        .toSortedList()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(dateList -> {
          for (long date : dateList) {
            if (mEvents.size() > 0 && mEvents.get(mEvents.size() - 1).getTimeInMillis() == date) {
              continue;
            }
            Event event = new Event(App.sContext.getResources().getColor(R.color.colorPrimary), date);
            mEvents.add(event);
          }
          Logger.d(mEvents.size() + "");
          mCompactcalendarView.addEvents(mEvents);
        }, throwable -> throwable.printStackTrace());
  }

  public void renderRecyclerView(Date date) {
    mRv.setVisibility(View.VISIBLE);
    mTvTitle.setVisibility(View.VISIBLE);
    List<Collection> collections = new ArrayList<>();
    for (Collection collection : mCollections){
      if(collection.getDate().equals(TimeUtil.toDateInYear(date))){
        collections.add(collection);
      }
    }
    mAdapter = new CollectionAdapter(collections);
    mRv.setAdapter(mAdapter);
    mAdapter.setOnItemClickListener((type, id) -> {
      switch (type){
        case Constant.TYPE_XJH:
          CareerDetailActivity.start(getActivity(),id);
          break;
        case Constant.TYPE_ZP:
          EmployDetailActivity.start(getActivity(),id);
          break;
        case Constant.TYPE_XZ:
          FulltimeDetailAcitivity.start(getActivity(),id);
          break;
      }
    });
    //Observable.from(mCollections)
    //    .filter(collection -> collection.getDate().equals(date.getTime()))
    //    .subscribeOn(Schedulers.immediate())
    //    .observeOn(Schedulers.immediate())
    //    .forEach();
  }

  private void hideRecyclerView() {
    mRv.setVisibility(View.GONE);
    mTvTitle.setVisibility(View.GONE);
  }

}

