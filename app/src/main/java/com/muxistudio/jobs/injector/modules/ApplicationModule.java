package com.muxistudio.jobs.injector.modules;

import android.content.Context;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.db.UserInfo;
import com.muxistudio.jobs.net.HttpLoggingInterceptor;
import com.muxistudio.jobs.net.OfflineInterceptor;
import com.muxistudio.jobs.net.RewriteInterceptor;
import com.muxistudio.jobs.net.TokenInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
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

    @Provides
    @Singleton
    public OkHttpClient provideTokenClient(TokenInterceptor tokenInterceptor) {
        File cacheFile = new File(App.sContext.getCacheDir(),"responses");
        int cacheSize = 1024 * 1024 * 10;
        Cache cache = new Cache(cacheFile,cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .addNetworkInterceptor(new RewriteInterceptor())
                .addInterceptor(new OfflineInterceptor())
                .addInterceptor(tokenInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
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
