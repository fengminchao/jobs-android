package com.muxistudio.jobs.bean;

/**
 * Created by ybao on 16/10/20.
 * 基本的返回值,返回 code 和 msg
 */

public class BaseData {

    private int code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
