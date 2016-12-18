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

  public UserStorge() {
    user = new User();
    user.setMail("");
    mUserInfo = new UserInfo();
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public String getToken() {
    if (token != null && !token.equals("")) {
      return token;
    }
    return user.getToken();
  }

  public UserInfo getUserInfo() {
    return mUserInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    mUserInfo = userInfo;
    if (mUserInfo.getAvator() == null) {
      mUserInfo.setAvator("");
    }
    if (mUserInfo.getBirth() == null) {
      mUserInfo.setBirth("");
    }
    if (mUserInfo.getCollege() == null) {
      mUserInfo.setCollege("");
    }
    if (mUserInfo.getGender() == null) {
      mUserInfo.setGender("");
    }
    if (mUserInfo.getLive() == null) {
      mUserInfo.setLive("");
    }
    if (mUserInfo.getMail() == null) {
      mUserInfo.setMail("");
    }
    if (mUserInfo.getMobile() == null) {
      mUserInfo.setMobile("");
    }
    if (mUserInfo.getPolitic() == null) {
      mUserInfo.setPolitic("");
    }
    if (mUserInfo.getName() == null){
      mUserInfo.setName("");
    }
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

  public boolean isLogin() {
    if (user != null && !TextUtils.isEmpty(user.getMail())) {
      return true;
    }
    return false;
  }

  public void login(User user, UserInfo userInfo) {
    this.user = user;
    this.mUserInfo = userInfo;
    PreferenceUtil.putString(PreferenceUtil.USER_MAIL, user.getMail());
  }

  public void logout() {
    this.setToken("");
    this.setAuthCode(0);
    //this.user = null;
    //this.mUserInfo = null;
    this.user.setMail("");
    this.user.setPwd("");
    this.mUserInfo.setMail("");
    PreferenceUtil.putString(PreferenceUtil.USER_MAIL, "");
  }
}
