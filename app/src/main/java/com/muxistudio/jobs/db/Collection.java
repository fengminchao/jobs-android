package com.muxistudio.jobs.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "COLLECTION".
 */
@Entity
public class Collection {
    private String mail;
    @Id
    private Long id;
    private String title;
    private String place;
    private String school;
    private String date;
    private String time;
    private String type;

    @Generated
    public Collection() {
    }

    @Generated
    public Collection(String mail, Long id, String title, String place, String school,
            String date, String time, String type) {
        this.mail = mail;
        this.id = id;
        this.title = title;
        this.place = place;
        this.school = school;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
