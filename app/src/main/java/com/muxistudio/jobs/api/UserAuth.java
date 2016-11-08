package com.muxistudio.jobs.api;

import android.content.Context;

import com.muxistudio.jobs.data.User;

/**
 * Created by ybao on 16/10/18.
 */

public class UserAuth {

    private User mUser;
    private String token;

    private Context mContext;

    public UserAuth(){
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
