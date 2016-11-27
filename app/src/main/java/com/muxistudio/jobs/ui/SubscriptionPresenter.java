package com.muxistudio.jobs.ui;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ybao on 16/11/15.
 */

public class SubscriptionPresenter {

  private CompositeSubscription mCompositeSubscription;

  public void addSubscription(Subscription subscription){
    if (mCompositeSubscription == null){
      mCompositeSubscription = new CompositeSubscription();
    }
    mCompositeSubscription.add(subscription);
  }

  public void detachView(){
    if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
      mCompositeSubscription.unsubscribe();
    }
  }
}
