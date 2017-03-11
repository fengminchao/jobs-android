package com.muxistudio.jobs.injector.modules;

import android.content.Context;

import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.db.UserInfo;
import com.muxistudio.jobs.net.HttpLoggingInterceptor;
import com.muxistudio.jobs.net.TokenAuthenticator;
import com.muxistudio.jobs.net.TokenInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by ybao on 16/10/16.
 */

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.context;
    }

    //@Provides @Singleton public OkHttpClient provideOkHttpClient(OkHttpClient client) {
    //  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    //  interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    //  OkHttpClient.Builder builder =
    //      client.newBuilder().connectTimeout(15, TimeUnit.SECONDS).addInterceptor(interceptor);
    //  return builder.build();
    //}

    @Provides
    @Singleton
    public OkHttpClient provideTokenClient(TokenInterceptor tokenInterceptor) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(tokenInterceptor)
                .authenticator(new TokenAuthenticator())
                .build();
        return client;
    }

    @Provides
    @Singleton
    TokenInterceptor provideTokenInterceptor(UserStorge userAuth) {
        return new TokenInterceptor(userAuth);
    }

    @Provides
    @Singleton
    public UserStorge provideUserAuth() {
        return new UserStorge();
    }

    @Provides
    @Singleton
    public UserInfo provideUserInfo() {
        return new UserInfo();
    }
}
