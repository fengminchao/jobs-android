package com.muxistudio.jobs.net;

import android.text.TextUtils;

import com.muxistudio.jobs.data.UserAuth;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ybao on 16/10/18.
 */

public class TokenInterceptor implements Interceptor {

    private UserAuth mUserAuth;

    public TokenInterceptor(UserAuth userAuth) {
        mUserAuth = userAuth;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        if (TextUtils.isEmpty(mUserAuth.getToken()) || hasAuthorizationHeader(originRequest)) {
            return chain.proceed(originRequest);
        }
        Request authorised = originRequest.newBuilder()
                .header("Authorization", mUserAuth.getToken())
                .build();
        return chain.proceed(authorised);
    }

    public boolean hasAuthorizationHeader(Request request) {
        if (TextUtils.isEmpty(request.header("Authorization"))) {
            return false;
        }
        return true;
    }
}
