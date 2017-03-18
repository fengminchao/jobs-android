package com.muxistudio.jobs.ui.collection;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.CollectionDao;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.SubscriptionPresenter;
import com.muxistudio.jobs.util.CollectionsUtil;
import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.util.ToastUtil;

import java.util.ArrayList;
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
public class CollectionPresenter extends SubscriptionPresenter
        implements CollectionContract.Presenter {

    private UserStorge mUserStorge;
    private UserApi mUserApi;
    private CollectionDao mCollectionDao;
    private List<Collection> mCollections;
    //存放删除的 collection
    private List<Collection> removedCollections = new ArrayList<>();

    private CollectionContract.View mView;

    @Inject
    public CollectionPresenter(UserStorge userStorge, UserApi userApi,
            CollectionDao collectionDao) {
        mUserStorge = userStorge;
        mUserApi = userApi;
        mCollectionDao = collectionDao;
    }

    @Override
    public void loadCollections() {
         mUserApi.getUserService()
                .getCollections()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(collectionResult -> {
                    mView.renderCollection(collectionResult.data);
                    return collectionResult.data;
                })
                .flatMap(Observable::from)
                .map(collection -> {
                    return collection.getId().intValue();
                })
                .toList()
                .subscribe(ids -> {
                    CollectionsUtil.putCollectionList(ids);
                }, throwable -> {
                    throwable.printStackTrace();
                    mView.showEmptyView();
                });
    }

    @Override
    public void onItemRemoved(Collection collection) {
        removedCollections.add(collection);
    }

    @Override
    public void onUndoClick(Collection collection) {
        removedCollections.remove(collection);
    }

    @Override
    public void attachView(@NonNull CollectionContract.View view) {
        mView = view;
        if (TextUtils.isEmpty(mUserStorge.getToken())){
            ToastUtil.showShort(R.string.tip_please_login);
        }
        loadCollections();
    }

    @Override
    public void detachView() {
        for (int i = 0; i < removedCollections.size(); i++) {
            final int pos = i;
            mUserApi.getUserService()
                    .removeCollection(removedCollections.get(i).getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(baseData -> {
                        if (baseData.code == 0) {
                            Logger.d("remove collection success");
                        }
                    }, throwable -> throwable.printStackTrace());
        }
        super.detachView();

    }
}
