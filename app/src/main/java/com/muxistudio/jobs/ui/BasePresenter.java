package com.muxistudio.jobs.ui;

import android.support.annotation.NonNull;

/**
 * Created by ybao on 16/10/19.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(@NonNull T view);

    void detachView();
}
