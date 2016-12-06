package com.muxistudio.jobs.ui.find;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.api.jobs.JobsApi;
import com.muxistudio.jobs.bean.InfoData;
import com.muxistudio.jobs.injector.PerActivity;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/11/10.
 */

@PerActivity public class InfoPresenter implements InfoContract.Presenter {

  private InfoContract.View mView;
  private JobsApi mJobsApi;
  //private int mPage = 1;

  private Subscription mSubscription;

  @Inject InfoPresenter(JobsApi jobsApi) {
    mJobsApi = jobsApi;
  }


  @Override public void loadData(int type, int page,boolean clean) {
    mView.showLoading();
    Logger.d(type + "");
    switch (type) {
      case 1:
        mSubscription = mJobsApi.getJobsService()
            .getCareerList(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(careerList -> careerList.data)
            .flatMap(Observable::from)
            .map(careerData -> {
              InfoData infoData = new InfoData();
              infoData.id = careerData.id;
              infoData.title = careerData.title;
              infoData.place = careerData.universityShortName + careerData.address;
              infoData.time = careerData.holdtime;
              infoData.logoUrl = careerData.logoUrl;
              infoData.clicks = careerData.totalClicks;
              mView.hideLoading();
              return infoData;
            })
            .toList()
            //.toSortedList((infoData, infoData2) -> infoData2.id.compareTo(infoData.id))
            .subscribe(infoDatas -> {
              if (clean){
                mView.renderInfoList(infoDatas);
                return;
              }
              mView.renderMoreData(infoDatas);
            }, throwable -> {
              throwable.printStackTrace();
            });
        break;
      case 2:
        mSubscription = mJobsApi.getJobsService()
            .getEmployList(page)
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
              mView.hideLoading();
              return infoData;
            })
            .toList()
            //.toSortedList((infoData, infoData2) -> infoData2.id.compareTo(infoData.id))
            .subscribe(infoDatas -> {
              if (clean) {
                mView.renderInfoList(infoDatas);
                return;
              }
              mView.renderMoreData(infoDatas);
            }, throwable -> {
              throwable.printStackTrace();
            });
        break;
      case 3:
        mSubscription = mJobsApi.getJobsService()
            .getFulltimeList(page)
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
              mView.hideLoading();
              return infoData;
            })
            .toList()
            //.toSortedList((infoData, infoData2) -> infoData2.id.compareTo(infoData.id))
            .subscribe(infoDatas -> {
              if (clean) {
                mView.renderInfoList(infoDatas);
                return;
              }
              mView.renderMoreData(infoDatas);
            }, throwable -> {
              throwable.printStackTrace();
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
    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mView.hideLoading();
      mSubscription.unsubscribe();
    }
  }
}
