package com.muxistudio.jobs.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ybao (ybaovv@gmail.com)
 * Date: 17/3/18
 */

public class RewriteInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        String cacheControl = originalResponse.header("Cache-Control");

        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains(
                "no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 10)
                    .build();
        } else {
            return originalResponse;
        }
    }
}
