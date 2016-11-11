package com.muxistudio.jobs.ui.find;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.api.jobs.JobsApi;
import com.muxistudio.jobs.bean.CareerList;
import com.muxistudio.jobs.bean.InfoData;
import com.muxistudio.jobs.injector.PerActivity;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/11/10.
 */

@PerActivity
public class InfoPresenter implements InfoContract.Presenter {

  private InfoContract.View mView;
  private JobsApi mJobsApi;
  private int mPage = 1;

  private Subscription mSubscription;

  @Inject InfoPresenter(JobsApi jobsApi) {
    mJobsApi = jobsApi;
  }

  @Override public void getInfoDataList(int type) {
    Logger.d(type + "");
    switch (type) {
      case 1:
        mSubscription = mJobsApi.getJobsService()
            .getCareerList(mPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(careerList -> careerList.data)
            .flatMap(Observable::from)
            .map(careerData -> {
              InfoData infoData = new InfoData();
              infoData.id = careerData.id;
              infoData.title = careerData.title;
              infoData.place = careerData.address;
              infoData.time = careerData.holdTime;
              infoData.logoUrl = careerData.logoUrl;
              infoData.clicks = careerData.totalClicks;
              return infoData;
            })
            .toSortedList()
            .subscribe(infoDatas -> {
              mView.renderInfoList(infoDatas);
            });
        break;
      case 2:
        mSubscription = mJobsApi.getJobsService()
            .getEmployList(mPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(employList -> employList.data)
            .flatMap(Observable::from)
            .map(employData -> {
              InfoData infoData = new InfoData();
              infoData.id = employData.id;
              infoData.title = employData.title;
              infoData.place = employData.venueName;
              infoData.time = employData.getTime();
              infoData.logoUrl = "";
              infoData.clicks = employData.totalClicks;
              return infoData;
            })
            .toSortedList()
            .subscribe(infoDatas -> {
              mView.renderInfoList(infoDatas);
            });
        break;
      case 3:
        mSubscription = mJobsApi.getJobsService()
            .getFulltimeList(mPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(fulltimeList -> fulltimeList.data)
            .flatMap(Observable::from)
            .map(fulltimeData -> {
              InfoData infoData = new InfoData();
              infoData.id = fulltimeData.id;
              infoData.title = fulltimeData.title;
              infoData.place = TextUtils.join(",", fulltimeData.positionCities);
              infoData.time = TextUtils.join(",", fulltimeData.positionNames);
              infoData.logoUrl = fulltimeData.logoUrl;
              infoData.clicks = -1;
              return infoData;
            })
            .toSortedList()
            .subscribe(infoDatas -> {
              mView.renderInfoList(infoDatas);
            });
        break;
    }
  }

  @Override public void onItemClick(int id) {

  }

  @Override public void attachView(@NonNull InfoContract.View view) {
    mView = view;
  }

  @Override public void detachView() {
    mView = null;

    if (mSubscription != null && mSubscription.isUnsubscribed() != false) {
      mSubscription.unsubscribe();
    }
  }
}
