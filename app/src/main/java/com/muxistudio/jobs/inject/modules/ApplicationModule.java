package com.muxistudio.jobs.inject.modules;

import android.content.Context;

import com.muxistudio.jobs.data.UserAuth;
import com.muxistudio.jobs.net.HttpLoggingInterceptor;
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
    public Context provideApplicationContext(){
        return this.context;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient client){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = client.newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideTokenClient(UserAuth auth){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(15,TimeUnit.SECONDS)
                .addNetworkInterceptor(new TokenInterceptor(auth))
                .build();
        return client;
    }


}
