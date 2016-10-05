package com.muxistudio.jobs.login;

/**
 * Created by ybao on 16/6/6.
 */
public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username,String password,OnLoginFinishedListener listener);

}
