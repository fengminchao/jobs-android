package com.muxistudio.jobs.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ybao on 16/12/6.
 */

public class CollectionResult {

  public int code;
  public String msg;
  public List<CollectionData> data;

}
