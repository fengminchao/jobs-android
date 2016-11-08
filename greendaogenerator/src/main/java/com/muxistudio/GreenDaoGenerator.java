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
    user.addIdProperty();
    user.addStringProperty("mail");
    user.addStringProperty("pwd");

    new DaoGenerator().generateAll(schema, GENERATE_CODE_PATH);
  }

}
