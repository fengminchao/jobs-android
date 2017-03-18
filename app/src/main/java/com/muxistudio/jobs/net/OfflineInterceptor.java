package com.muxistudio.jobs.net;

import com.muxistudio.jobs.util.Logger;
import com.muxistudio.jobs.util.NetUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ybao (ybaovv@gmail.com)
 * Date: 17/3/18
 */

public class OfflineInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtil.isNetWorkAvailable()) {
            int maxStale = 60 * 60 * 24 * 28;
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return chain.proceed(request);
    }
}
