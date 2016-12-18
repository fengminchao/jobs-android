package com.muxistudio.jobs;

/**
 * Created by ybao on 16/10/16.
 */

public class Constant {

  public static final String BASE_USER_API = "182.254.247.206:3000/api/";

  //存储图片的位置
  public static final String STORGE_PATH =
      App.sContext.getExternalCacheDir() == null ? App.sContext.getCacheDir().getAbsolutePath()
          : App.sContext.getExternalCacheDir().getAbsolutePath();

  //默认头像图片
  public static final String DEFAULT_AVATOR_URL =
      "http://ognz3v7yx.bkt.clouddn.com/avator/default.jpeg";

  public static final String QINIU_URL = "http://ognz3v7yx.bkt.clouddn.com/";

  public static final int TYPE_XJH = 1;
  public static final int TYPE_ZP = 2;
  public static final int TYPE_XZ = 3;

  public static final String[] TAG_ARRAY = {
      "IT信息","金融","教育","传媒","建筑","物流","贸易","医学","服务业","政府部门",
      "制造","交通","运营"
  };
}
