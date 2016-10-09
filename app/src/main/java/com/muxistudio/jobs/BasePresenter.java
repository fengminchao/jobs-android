package com.muxistudio.jobs;

import android.support.annotation.NonNull;

/**
 * Created by ybao on 16/10/5.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(@NonNull T view);

    void detachView();
}
