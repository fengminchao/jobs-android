package com.muxistudio.jobs.injector.modules;

import com.muxistudio.jobs.api.user.UserApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by ybao on 16/10/19.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public UserApi provideUserApi(OkHttpClient client){
        return new UserApi(client);
    }
}
