package com.muxistudio.jobs.bean;

import android.os.Parcelable;
import java.io.Serializable;

/**
 * Created by ybao on 16/10/20.
 * 基本的返回值,返回 code 和 msg
 * 来自服务器端的 json 数据
 */

public class BaseData {

  public int code;
  public String msg;

}
