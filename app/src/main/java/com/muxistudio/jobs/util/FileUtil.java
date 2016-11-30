package com.muxistudio.jobs.util;

import com.muxistudio.jobs.Constant;
import java.io.File;

/**
 * Created by ybao on 16/11/30.
 */

public class FileUtil {

  public static String getAvatorFromDisk(String mail){
    File f = new File(Constant.STORGE_PATH + "/avator" + mail + ".png");
    if (f.exists()){
      return f.getAbsolutePath();
    }else {
      return "";
    }
  }
}
