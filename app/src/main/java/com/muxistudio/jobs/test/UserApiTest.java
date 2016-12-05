package com.muxistudio.jobs.test;

import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.User;
import com.muxistudio.jobs.net.TokenInterceptor;
import com.muxistudio.jobs.util.Logger;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.schedulers.Schedulers;

/**
 * Created by ybao on 16/12/5.
 */

public class UserApiTest {

  public static void main(String[] args){
    User user = new User();
    user.setMail("jobsmailer@163.com");
    UserStorge userStorge = new UserStorge();
    TokenInterceptor tokenInterceptor = new TokenInterceptor(userStorge);
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .connectTimeout(8, TimeUnit.SECONDS)
        .build();
    UserApi userApi = new UserApi(client);
    userApi.getUserService().authNewMail(user)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(baseData -> Logger.d(baseData.code + ""),throwable -> throwable.printStackTrace());
  }
}
