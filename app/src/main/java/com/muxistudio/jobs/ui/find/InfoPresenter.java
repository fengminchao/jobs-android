package com.muxistudio.jobs.ui.find;

import android.support.annotation.NonNull;
import com.muxistudio.jobs.api.jobs.JobsApi;
import com.muxistudio.jobs.bean.CareerList;
import com.muxistudio.jobs.bean.InfoData;
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

public class InfoPresenter implements InfoContract.Presenter{

  private InfoContract.View mView;
  private JobsApi mJobsApi;
  private int mPage = 1;

  @Inject public InfoPresenter(JobsApi jobsApi) {
    mJobsApi = jobsApi;
  }

  @Override public void getInfoDataList(int type) {
    Observable observable = null;
    Subscription subscription = null;
    List<String> s = null;
    switch (type){
      case 1:
        observable = mJobsApi.getJobsService().getCareerList(mPage);
        mJobsApi.getJobsService().getCareerList(mPage)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map(careerList -> careerList.data)
            .flatMap((careerDatas -> Observable.from(careerDatas)))
            //.flatMap(careerData -> {
            //});
        break;
      case 2:
        observable = mJobsApi.getJobsService().getEmployList(mPage);
        break;
      case 3:
        observable = mJobsApi.getJobsService().getFulltimeList(mPage);
        break;
    }
    if (observable != null){
      observable.subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }
  }

  @Override public void onItemClick(int id) {

  }

  @Override public void attachView(@NonNull InfoContract.View view) {
   mView = view;
  }

  @Override public void detachView() {
    mView = null;
  }
}
