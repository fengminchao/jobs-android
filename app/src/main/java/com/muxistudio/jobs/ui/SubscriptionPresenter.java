package com.muxistudio.jobs.ui;

import com.muxistudio.jobs.util.Logger;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ybao on 16/11/15.
 */

public class SubscriptionPresenter {

    private CompositeSubscription mCompositeSubscription;

    public void addSubscription(Subscription subscription) {
        Logger.d("subpresenter add");
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void detachView() {
        Logger.d("subpresenter detach");
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
