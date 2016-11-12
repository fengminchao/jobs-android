package com.muxistudio.jobs.api.user;

import com.muxistudio.jobs.bean.BaseData;
import com.muxistudio.jobs.bean.TokenData;
import com.muxistudio.jobs.data.UserStorge;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Created by ybao on 16/10/18.
 */

public interface UserService {

    //登录版块
    @POST("user/auth")
    Observable<BaseData> authNewMail(@Body String mail);

    @POST("user/regiseter")
    Observable<BaseData> registerByAuth(@Body String mail,
                                        @Body String pwd,
                                        @Body String authCode);

    @POST("user/login")
    Observable<TokenData> login(@Body UserStorge user);

    @POST("user/findAuth")
    Observable<BaseData> findAuth(@Body String mail);

    @POST("user/find")
    Observable<BaseData> findByAuth(@Body String mail,
                                    @Body String authCode);

    @PUT("user/")
    Observable<TokenData> changePwd(@Body String mail,
                                    @Body String oldPwd,
                                    @Body String newPwd);


    //收藏版块
}
