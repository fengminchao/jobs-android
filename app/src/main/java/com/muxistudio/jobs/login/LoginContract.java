package com.muxistudio.jobs.login;

/**
 * Created by ybao on 16/10/5.
 */

public interface LoginContract {

    interface View {

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

