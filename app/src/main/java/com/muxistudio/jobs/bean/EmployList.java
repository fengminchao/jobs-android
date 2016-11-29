package com.muxistudio.jobs.bean;

import java.util.List;

/**
 * Created by ybao on 16/11/9.
 */

public class EmployList {

  public List<EmployData> data;

  public class EmployData {
    public int id;
    public String title;
    public String holdtime;
    public String detailtime;
    public String venueName;
    public int totalClicks;

    public String getTime(){
      return holdtime + " " + detailtime;
    }
  }
}
