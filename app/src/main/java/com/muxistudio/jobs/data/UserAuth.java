package com.muxistudio.jobs.data;

/**
 * Created by ybao on 16/10/18.
 */

public class UserAuth {

    private User mUser;
    private String token;

    public UserAuth(User user,String token){
        this.mUser = user;
        this.token = token;
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
