package com.muxistudio.jobs.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ybao on 16/11/9.
 */

public class CareerList extends JobsBase{

  public List<CareerData> data;

  public class CareerData {
    public int id;
    public String title;
    public String holdTime;
    public String universityShortName;
    public String address;
    public String logoUrl;
    public int totalClicks;
  }
}
