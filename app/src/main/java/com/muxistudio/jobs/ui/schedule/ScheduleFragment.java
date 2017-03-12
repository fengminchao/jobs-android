package com.muxistudio.jobs.ui.schedule;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.muxistudio.jobs.App;
import com.muxistudio.jobs.Constant;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.BaseFragment;
import com.muxistudio.jobs.ui.collection.CollectionAdapter;
import com.muxistudio.jobs.ui.find.detail.CareerDetailActivity;
import com.muxistudio.jobs.ui.find.detail.EmployDetailActivity;
import com.muxistudio.jobs.ui.find.detail.FulltimeDetailAcitivity;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.util.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ybao on 16/12/8.
 */

@PerActivity
public class ScheduleFragment extends BaseFragment {

    @BindView(R.id.compactcalendar_view)
    CompactCalendarView mCompactcalendarView;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @Inject
    UserStorge mUserStorge;
    @Inject
    CollectionDao mCollectionDao;
    @Inject
    UserApi mUserApi;
    private CollectionAdapter mAdapter;
    private List<Collection> mCollections;
    private List<Event> mEvents;

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    @Override
    public void initInjector() {
        ((MainActivity) getActivity()).getComponent().inject(this);
    }

    @Override
    public int loadContentView() {
        return R.layout.fragment_schedule;
    }

    @Override
    protected void initView(View view) {
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
        ((MainActivity) getActivity()).setTitle((date.getMonth() + 1) + "月");
        //addEvent();

        List<Long> list = new ArrayList<>();
        for (int i = 0; i < mCollections.size(); i++) {
            if (mCollections.get(i).getDate().equals("")) {
                continue;
            }
            Date date1 = TimeUtil.parseDateStrToDate(mCollections.get(i).getDate());
            long time = date1.getTime();
            list.add(time);
        }
        Set<Long> set = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(set);
        mEvents = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Event event = new Event(App.sContext.getResources().getColor(R.color.colorPrimary),
                    list.get(i));
            Logger.d(event.getTimeInMillis() + "");
            Logger.d(TimeUtil.toDateInYear(new Date(list.get(i))));
            mEvents.add(event);
        }
        mCompactcalendarView.addEvents(mEvents);

        //Date date1 = new Date(System.currentTimeMillis());
        //date1.setDate(1);
        //Date date2 = new Date(System.currentTimeMillis());
        //date2.setDate(2);
        //Event ev1 = new Event(Color.GREEN, date1.getTime());
        //Event ev2 = new Event(Color.GRAY,date2.getTime());
        //List<Event> events = new ArrayList<>();
        //events.add(ev1);
        //events.add(ev2);
        ////mCompactcalendarView.addEvent(ev1, false);
        //mCompactcalendarView.addEvents(events);

        mCompactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                if (mCompactcalendarView.getEvents(dateClicked) != null
                        && mCompactcalendarView.getEvents(dateClicked).size() > 0) {
                    renderRecyclerView(dateClicked);
                } else {
                    hideRecyclerView();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                ((MainActivity) getActivity()).setTitle((firstDayOfNewMonth.getMonth() + 1) + "月");

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
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < mCollections.size(); i++) {
            if (mCollections.get(i).getDate().equals("")) {
                continue;
            }
            Date date = TimeUtil.parseDateStrToDate(mCollections.get(i).getDate());
            long time = date.getTime();
            list.add(time);
        }
        Set<Long> set = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(set);
        mEvents = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Event event = new Event(App.sContext.getResources().getColor(R.color.colorPrimary),
                    list.get(i));
            mEvents.add(event);
        }
        mCompactcalendarView.addEvents(mEvents);
        //Observable.from(mCollections)
        //    .subscribeOn(Schedulers.io())
        //    .map(mCollections -> mCollections.getDate())
        //    .filter(s -> !TextUtils.isEmpty(s))
        //    .map(s -> {
        //      Date date = TimeUtil.parseDateStrToDate(s);
        //      return date.getTime();
        //    })
        //    .toSortedList()
        //    .observeOn(AndroidSchedulers.mainThread())
        //    .subscribe(dateList -> {
        //      for (long date : dateList) {
        //        if (mEvents.size() > 0 && mEvents.get(mEvents.size() - 1).getTimeInMillis() ==
        // date) {
        //          continue;
        //        }
        //        Event event = new Event(App.sContext.getResources().getColor(R.color
        // .colorPrimary), date);
        //        mEvents.add(event);
        //      }
        //      Logger.d(mEvents.size() + "");
        //      mCompactcalendarView.addEvents(mEvents);
        //    }, throwable -> throwable.printStackTrace());
    }

    public void renderRecyclerView(Date date) {
        mRv.setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);
        List<Collection> collections = new ArrayList<>();
        for (Collection collection : mCollections) {
            if (collection.getDate().equals(TimeUtil.toDateInYear(date))) {
                collections.add(collection);
            }
        }
        mAdapter = new CollectionAdapter(collections);
        mRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((type, id) -> {
            switch (type) {
                case Constant.TYPE_XJH:
                    CareerDetailActivity.start(getActivity(), id);
                    break;
                case Constant.TYPE_ZP:
                    EmployDetailActivity.start(getActivity(), id);
                    break;
                case Constant.TYPE_XZ:
                    FulltimeDetailAcitivity.start(getActivity(), id);
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
