package com.muxistudio.jobs.net;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.RxBus;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.api.user.UserApi;
import com.muxistudio.jobs.db.UserDao;
import com.muxistudio.jobs.event.RefreshTokenEvent;
import com.muxistudio.jobs.util.PreferenceUtil;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by ybao (ybaovv@gmail.com)
 * Date: 17/3/11
 */

public class TokenAuthenticator implements Authenticator{

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
//        UserApi userApi = new UserApi();
//        String token = userApi.getUserService().loginSync(App.getUserStorge().getUser())
//                .execute().body().token;
//        App.updateToken(token);
//        RxBus.getDefault().send(new RefreshTokenEvent());
        return null;
    }
}