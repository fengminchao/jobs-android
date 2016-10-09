package com.muxistudio.jobs.login;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by ybao on 16/6/6.
 */
public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mLoginView;

    public LoginPresenter() {

    }

    @Override
    public void login(String name, String pwd) {
        mLoginView.showLoading();
        if(TextUtils.isEmpty(name)){
            mLoginView.showUserNameError("user name not null");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            mLoginView.showUserPwdError("user pwd not null");
            return;
        }
        new Handler().postDelayed(() -> {
            mLoginView.hideLoading();
            Log.d("login ","login success");
            mLoginView.loginSuccess();
        },2000);
    }


    @Override
    public void attachView(@NonNull LoginContract.View view) {
        mLoginView = view;
    }

    @Override
    public void detachView() {

    }
}
