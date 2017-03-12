package com.muxistudio.jobs.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "USER_INFO".
 */
@Entity
public class UserInfo {

    @Id
    private String mail;
    private String avator;
    private String name;
    private String gender;
    private String live;
    private String birth;
    private String politic;
    private String college;
    private String mobile;

    @Generated
    public UserInfo() {
    }

    public UserInfo(String mail) {
        this.mail = mail;
    }

    @Generated
    public UserInfo(String mail, String avator, String name, String gender, String live,
            String birth, String politic, String college, String mobile) {
        this.mail = mail;
        this.avator = avator;
        this.name = name;
        this.gender = gender;
        this.live = live;
        this.birth = birth;
        this.politic = politic;
        this.college = college;
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPolitic() {
        return politic;
    }

    public void setPolitic(String politic) {
        this.politic = politic;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
