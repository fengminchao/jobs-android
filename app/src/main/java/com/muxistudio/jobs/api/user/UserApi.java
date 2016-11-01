package com.muxistudio.jobs.api.user;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ybao on 16/10/19.
 */

public class UserApi {

    private UserService mUserService;

    private static final String BASE_USER_URL = "http://182.254.247.206:3000/api/";

    public UserApi(OkHttpClient client) {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor()
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        mUserService = retrofit.create(UserService.class);
    }

    public UserService getUserService(){
        return mUserService;
    }
}
