package com.muxistudio.jobs.ui.login;

import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.api.UserAuth;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.bean.TokenData;
import com.muxistudio.jobs.data.User;
import com.muxistudio.jobs.injector.PerActivity;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/10/19.
 */

@PerActivity
public class LoginPresenter implements LoginContract.Presenter{

    private UserApi mUserApi;
    private UserAuth mUserAuth;

    private LoginContract.View mLoginView;

    @Inject
    public LoginPresenter(UserApi userApi, UserAuth userAuth) {
        mUserApi = userApi;
        mUserAuth = userAuth;
    }

    @Override
    public void attachView(LoginContract.View view) {
        mLoginView = view;
    }

    @Override
    public void detachView() {
        mLoginView = null;
    }

    @Override
    public void login(String username, String pwd) {
        User user = new User();
        user.setMail("jobsmailer@163.com");
        user.setPwd("123");
        mUserApi.getUserService().login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TokenData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(TokenData tokenData) {
                        Logger.d("get the token");
                        Logger.d(tokenData.token + "");
                    }
                });
    }
}
