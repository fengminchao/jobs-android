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

  /**
   * {mail}
   * @param user
   * @return
   */
    @POST("user/auth")
    Observable<BaseData> authNewMail(@Body User user);

  /**
   * {mail,pwd,authCode}
   * @param user
   * @return
   */
    @POST("user/regiseter")
    Observable<BaseData> registerByAuth(@Body User user);

    @POST("user/login")
    Observable<TokenResult> login(@Body User user);

  /**
   * {mail}
   * @param user
   * @return
   */
    //忘记密码验证
    @POST("user/findAuth")
    Observable<BaseData> findAuth(@Body User user);

  /**
   * {mail,pwd,authCode}
   * @param user
   * @return
   */
    //忘记密码修改
    @POST("user/find")
    Observable<BaseData> findByAuth(@Body User user);

    //修改密码
    @PUT("user/")
    Observable<TokenResult> changePwd(@Body String mail,
                                    @Body String oldPwd,
                                    @Body String newPwd);

    @GET("user/info/")
    Observable<UserInfoResult> getUserInfo();

    @PUT("user/info/")
    Observable<BaseData> updateUserInfo(@Body UserInfo userInfo, @Header("token") String token);

    //key为要上传的文件名 格式为：avator/usermail.png
    @GET("http://182.254.247.206:8400/jobsapp/token")
    Observable<Response<TokenResult>> getUploadToken(@Header("key") String key,@Header("isFirst") String isFirst);

    //更新的时候删除用户之前的头像
    @DELETE("182.254.247.206:8400/jobsapp/{usermail}")
    Observable<Response> deleteAvator(@Path("usermail")String mail);

    //收藏版块
}
