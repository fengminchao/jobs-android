package com.muxistudio.jobs.login;

import com.muxistudio.jobs.BasePresenter;
import com.muxistudio.jobs.BaseView;

/**
 * Created by ybao on 16/10/5.
 */

public interface LoginContract {

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showUserNameError(String err);

        void showUserPwdError(String err);

        void loginSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void login(String name, String pwd);
    }
}

