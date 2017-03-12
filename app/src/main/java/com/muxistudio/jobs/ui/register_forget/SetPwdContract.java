package com.muxistudio.jobs.ui.register_forget;

import com.muxistudio.jobs.ui.BasePresenter;
import com.muxistudio.jobs.ui.BaseView;

/**
 * Created by ybao on 16/10/22.
 */

public class SetPwdContract {

    interface View extends BaseView {

        void showMailError(String msg);

        void showPwdError(String msg);

        void showAuthCodeError(String msg);

        void showError(String msg);

        void showLoading();

        void hideLoading();

        void showAuthSend();

        //验证成功后跳转到登录界面
        void authSuccess();
    }

    interface Presenter extends BasePresenter<View> {

        void sendAuthCode(String mail);

        void sendResetAuthCode(String mail);

        void register(String mail, String pwd, String auth);

        void resetPwd(String mail, String pwd, String auth);
    }
}
