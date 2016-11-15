package com.muxistudio.jobs.api;

import android.text.TextUtils;
import com.muxistudio.jobs.db.User;
import com.muxistudio.jobs.db.UserInfo;
import com.muxistudio.jobs.util.PreferenceUtil;

/**
 * Created by ybao on 16/10/18.
 */

public class UserStorge {

    private User user;
    private UserInfo mUserInfo;
    private String token;
    private int authCode;

    public UserStorge(){
        user = new User();
        mUserInfo = new UserInfo();
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        if(token != null && !token.equals("")){
            return token;
        }
        return user.getToken();
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }

    public void setToken(String token) {
        this.token = token;
        this.user.setToken(token);
    }

    public int getAuthCode() {
        return user.getAuthCode();
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public boolean isLogin(){
        if (user != null && !TextUtils.isEmpty(user.getMail())){
            return true;
        }
        return false;
    }

    public void login(User user,UserInfo userInfo){
        this.user = user;
        this.mUserInfo = userInfo;
        PreferenceUtil.putString(PreferenceUtil.USER_MAIL,user.getMail());
    }

    public void logout(){
        this.user = null;
        this.mUserInfo = null;
        this.setToken("");
        this.setAuthCode(0);
        PreferenceUtil.putString(PreferenceUtil.USER_MAIL,"");
    }

}
