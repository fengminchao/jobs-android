package com.muxistudio.jobs.ui.collection;

import android.support.annotation.NonNull;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.bean.CollectionData;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.SubscriptionPresenter;
import com.muxistudio.jobs.util.Logger;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/12/6.
 */

@PerActivity
public class CollectionPresenter extends SubscriptionPresenter implements CollectionContract.Presenter{

  private UserStorge mUserStorge;
  private UserApi mUserApi;
  private CollectionDao mCollectionDao;
  private List<Collection> mCollections;
  //存放删除的 collection
  private List<Collection> removedCollections;

  private CollectionContract.View mView;

  @Inject public CollectionPresenter(UserStorge userStorge, UserApi userApi) {
    mUserStorge = userStorge;
    mUserApi = userApi;
  }

  @Override public void loadCollections() {
    Subscription s = mUserApi.getUserService().getCollections()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(collectionResult -> {
          if (collectionResult.code != 0){
            mView.showEmptyView();
            return;
          }
          mView.renderCollection(collectionResult.data);
        },throwable -> throwable.printStackTrace());
    addSubscription(s);
  }

  @Override public void onItemRemoved(Collection collection) {
    Subscription s = mUserApi.getUserService().removeCollection(collection.getId())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(baseData -> {
          if (baseData.code == 0){
            mView.showSnackerbar("收藏信息删除成功");
          }
        },throwable -> {
          throwable.printStackTrace();
          mView.insertItem(collection);
          mView.showError("收藏信息删除失败");
        });
    addSubscription(s);
  }

  @Override public void onUndoClick(Collection collection) {
    Subscription s = mUserApi.getUserService().addCollection(collection)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(baseData -> {
          if (baseData.code == 0){
            mView.insertItem(collection);
          }
        },throwable -> {
          throwable.printStackTrace();
          mView.showError("发生未知错误");
        });
    addSubscription(s);
  }

  @Override public void attachView(@NonNull CollectionContract.View view) {
    mView = view;
    loadCollections();
  }

  @Override public void detachView() {
    super.detachView();
    for (int i = 0;i < removedCollections.size();i ++){
      mUserApi.getUserService().removeCollection(removedCollections.get(i).getId())
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(baseData -> {
            if (baseData.code == 0){
              Logger.d("remove collection success");
            }
          },throwable -> throwable.printStackTrace());
    }

  }
}
