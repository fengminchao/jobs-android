package com.muxistudio.jobs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ybao on 16/11/29.
 * 解析日期时间
 */

public class TimeUtil {

  public static String toDate(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("MM-dd");
    return dateFormat.format(date);
  }

  public static String toDateInYear(Date date){
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return format.format(date);
  }

  public static String parseDate(String s){
    try {
      String date = s.substring(0,10);
      return date;
    }catch (Exception e){
      e.printStackTrace();
    }
    return "";
  }

  public static String parseTime(String s){
    try {
      String time = s.substring(11);
      return time;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public static String parseStartTime(String s){
    try {
      String time = s.substring(0,5);
      return time;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public static Calendar parseDateToCalendar(String date){
    Calendar c = Calendar.getInstance();
    int h1 = date.indexOf("-");
    int h2 = date.lastIndexOf("-");
    c.set(Calendar.YEAR, Integer.valueOf(date.substring(0, h1)));
    c.set(Calendar.MONTH, Integer.valueOf(date.substring(h1 + 1, h2)));
    c.set(Calendar.DAY_OF_MONTH, Integer.valueOf(date.substring(h2 + 1)));
    return c;
  }

  public static Date parseDateStrToDate(String datestr){
    Date date = new Date();
    int h1 = datestr.indexOf("-");
    int h2 = datestr.lastIndexOf("-");
    date.setYear(Integer.valueOf(datestr.substring(0,h1)));
    //月份为 0-11,比较特殊
    date.setMonth(Integer.valueOf(datestr.substring(h1 + 1,h2)) - 1);
    date.setDate(Integer.valueOf(datestr.substring(h2 + 1)));
    return date;
  }
}
