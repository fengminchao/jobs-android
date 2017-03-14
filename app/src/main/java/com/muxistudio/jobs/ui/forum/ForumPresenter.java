package com.muxistudio.jobs.ui.forum;

import android.support.annotation.NonNull;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.injector.PerActivity;

import java.util.Collections;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao (ybaovv@gmail.com)
 * Date: 17/3/12
 */

@PerActivity
public class ForumPresenter implements ForumContract.Presenter {

    @Inject
    UserApi mUserApi;
    private ForumContract.View forumView;

    @Inject
    public ForumPresenter(UserApi userApi) {
        mUserApi = userApi;
    }

    @Override
    public void attachView(@NonNull ForumContract.View view) {
        forumView = view;
    }

    @Override
    public void detachView() {
        forumView = null;
    }

    @Override
    public void onPostItemClick(int position) {

    }

    @Override
    public void loadPostList() {
        mUserApi.getUserService().getPostList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(postListResult -> {
                    Collections.reverse(postListResult.data);
                    forumView.renderPostList(postListResult.data, true);
                }, throwable -> {
                    throwable.printStackTrace();
                    forumView.showError(App.sContext.getString(R.string.err_net));
                }, () -> forumView.hideRefreshView());
    }
}
