package com.muxistudio.jobs.api.user;

import com.muxistudio.jobs.bean.BaseData;
import com.muxistudio.jobs.bean.TokenResult;
import com.muxistudio.jobs.bean.UserInfoResult;
import com.muxistudio.jobs.data.UserStorge;

import com.muxistudio.jobs.db.User;
import com.muxistudio.jobs.db.UserInfo;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ybao on 16/10/18.
 */

public interface UserService {

    //登录版块

    //用户注册验证
    @POST("user/auth")
    Observable<BaseData> authNewMail(@Body String mail);

    @POST("user/regiseter")
    Observable<BaseData> registerByAuth(@Body String mail,
                                        @Body String pwd,
                                        @Body String authCode);

    @POST("user/login")
    Observable<TokenResult> login(@Body User user);

    //忘记密码验证
    @POST("user/findAuth")
    Observable<BaseData> findAuth(@Body String mail);

    //忘记密码修改
    @POST("user/find")
    Observable<BaseData> findByAuth(@Body String mail,
                                    @Body String authCode);

    //修改密码
    @PUT("user/")
    Observable<TokenResult> changePwd(@Body String mail,
                                    @Body String oldPwd,
                                    @Body String newPwd);

    @GET("info/")
    Observable<UserInfoResult> getUserInfo();

    @PUT("info/")
    Observable<BaseData> updateUserInfo(@Body UserInfo userInfo);

    //key为要上传的文件名 格式为：avator/usermail.png
    @GET("182.254.247.206:8400/jobsapp/token")
    Observable<Response> getUploadToken(@Header("key") String key);

    //更新的时候删除用户之前的头像
    @DELETE("182.254.247.206:8400/jobsapp/{usermail}")
    Observable<Response> deleteAvator(@Path("usermail")String mail);

    //收藏版块
}
