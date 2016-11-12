package com.muxistudio.jobs.net;

import android.text.TextUtils;

import com.muxistudio.jobs.Logger;
import com.muxistudio.jobs.api.UserStorge;

import com.muxistudio.jobs.api.jobs.JobsApi;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ybao on 16/10/18.
 */

public class TokenInterceptor implements Interceptor {

    private UserStorge mUserStorge;

    public TokenInterceptor(UserStorge userStorge) {
        mUserStorge = userStorge;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        //Logger.d(mUserStorge.getToken());
        Request authorised = null;
        Logger.d(originRequest.url().host());
        if (!originRequest.url().host().equals("api.haitou.cc")) {
            authorised = originRequest.newBuilder()
                    .header("Authorization", mUserStorge.getToken())
                    .build();
            return chain.proceed(authorised);
        }

        return chain.proceed(originRequest);
    }

    public boolean hasAuthorizationHeader(Request request) {
        if (TextUtils.isEmpty(request.header("Authorization"))) {
            return false;
        }
        return true;
    }
}
