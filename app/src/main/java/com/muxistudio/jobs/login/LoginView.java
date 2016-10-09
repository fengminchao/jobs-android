package com.muxistudio.jobs.login;

/**
 * Created by ybao on 16/6/6.
 */
public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
