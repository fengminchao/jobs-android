package com.muxistudio.jobs.ui.register;

import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;

/**
 * Created by ybao on 16/10/22.
 */

public class RegisterContract {

    interface View extends BaseView{

        void showNameErr();

        void showPwdErr(String err);

        void showAuthErr(String err);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends BasePresenter{

        void getAuthCode();

        void register();
    }
}
