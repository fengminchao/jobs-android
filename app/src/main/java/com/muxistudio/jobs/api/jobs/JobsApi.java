package com.muxistudio.jobs.api.jobs;

import com.muxistudio.jobs.bean.CareerList;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ybao on 16/10/18.
 */

public class JobsApi {

    private JobsService mJobsService;

    private static final String BASE_JOBS_URL = "http://api.haitou.cc/";

    public JobsApi(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_JOBS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

       mJobsService = retrofit.create(JobsService.class);
    }

    public JobsService getJobsService(){
        return mJobsService;
    }
}
