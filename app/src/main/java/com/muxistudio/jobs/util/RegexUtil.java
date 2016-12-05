package com.muxistudio.jobs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ybao on 16/12/4.
 */

public class RegexUtil {

  /**
   * 检验邮箱
   * @param mail
   * @return
   */
  public static boolean checkMail(String mail) {
    try {
      String check =
          "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
      Pattern regex = Pattern.compile(check);
      Matcher matcher = regex.matcher(mail);
      boolean isMatched = matcher.matches();
      return isMatched;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean checkStr(String regexStr,String str){
    try {
      Pattern regex = Pattern.compile(regexStr);
      Matcher matcher = regex.matcher(str);
      return matcher.matches();
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }
}
