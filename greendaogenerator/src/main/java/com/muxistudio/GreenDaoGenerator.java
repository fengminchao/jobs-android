package com.muxistudio;

import java.io.IOException;
import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreenDaoGenerator {

  public static final int VERSION = 1;
  public static final String GENERATE_CODE_PATH = "../JobsApp/app/src/main/java";

  public static void main(String[] args) throws Exception{
    Schema schema = new Schema(1000, "com.muxistudio.jobs.db");

    Entity user = schema.addEntity("User");
    user.addStringProperty("mail").primaryKey();
    //加密存储和发送
    user.addStringProperty("pwd");
    user.addStringProperty("token");
    user.addIntProperty("authCode");

    Entity userInfo = schema.addEntity("UserInfo");
    userInfo.addStringProperty("mail").primaryKey();
    userInfo.addStringProperty("avator");
    userInfo.addStringProperty("name");
    userInfo.addStringProperty("gender");
    userInfo.addStringProperty("live");
    userInfo.addStringProperty("birth");
    userInfo.addStringProperty("politic");
    userInfo.addStringProperty("college");
    userInfo.addStringProperty("mobile");

    Entity collection = schema.addEntity("Collection");
    collection.addStringProperty("mail");
    collection.addIntProperty("id");
    collection.addStringProperty("title");
    collection.addStringProperty("place");
    collection.addStringProperty("school");
    collection.addStringProperty("date");
    collection.addStringProperty("time");
    collection.addStringProperty("type");

    Entity job = schema.addEntity("Jobs");
    job.addIntProperty("id").primaryKey();
    job.addStringProperty("logoUrl");
    job.addStringProperty("title");
    job.addStringProperty("place");
    job.addStringProperty("time");
    job.addIntProperty("clicks");

    Entity history = schema.addEntity("History");
    history.addIdProperty();
    history.addStringProperty("query");
    history.addStringProperty("mail");

    new DaoGenerator().generateAll(schema, GENERATE_CODE_PATH);
  }

}
