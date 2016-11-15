package com.muxistudio.jobs.bean;

/**
 * Created by ybao on 16/11/9.
 */

public class UserResult extends BaseData {

  public Data data;

  public class Data{

    public String mail;
    public String pwd;
    public String token;
    public int authCode;

  }
}
