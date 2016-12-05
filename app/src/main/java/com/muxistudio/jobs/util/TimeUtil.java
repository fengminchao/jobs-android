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
    c.set(Calendar.YEAR,Integer.valueOf(date.substring(0,4)));
    c.set(Calendar.MONTH,Integer.valueOf(date.substring(5,7)));
    c.set(Calendar.DAY_OF_MONTH,Integer.valueOf(8));
    return c;
  }
}
