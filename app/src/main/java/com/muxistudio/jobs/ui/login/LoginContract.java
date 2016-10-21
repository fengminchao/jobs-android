package com.muxistudio.jobs.ui.login;

import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;

/**
 * Created by ybao on 16/10/19.
 */

public class LoginContract {

    interface View extends BaseView {

        void showError();

        void showLoading();

        void hideLoading();

        void loginSuccess();

    }

    interface Presenter extends BasePresenter<View> {

        void login(String username, String pwd);
    }
}
