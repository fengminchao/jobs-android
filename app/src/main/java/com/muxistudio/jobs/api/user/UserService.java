package com.muxistudio.jobs.api.user;

import com.muxistudio.jobs.bean.BaseData;
import com.muxistudio.jobs.bean.CollectionResult;
import com.muxistudio.jobs.bean.PostContent;
import com.muxistudio.jobs.bean.PostData;
import com.muxistudio.jobs.bean.PostDetailResult;
import com.muxistudio.jobs.bean.PostListResult;
import com.muxistudio.jobs.bean.ReplyBean;
import com.muxistudio.jobs.bean.TokenResult;
import com.muxistudio.jobs.bean.UserInfoResult;
import com.muxistudio.jobs.db.Collection;
import com.muxistudio.jobs.db.User;
import com.muxistudio.jobs.db.UserInfo;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
     */
    @POST("user/auth")
    Observable<BaseData> authNewMail(@Body User user);

    /**
     * {mail,pwd,authCode}
     */
    @POST("user/register")
    Observable<BaseData> registerByAuth(@Body User user);

    @POST("user/login")
    Observable<TokenResult> login(@Body User user);

    @POST("user/login")
    Call<TokenResult> loginSync(@Body User user);

    /**
     * {mail}
     */
    //忘记密码验证
    @POST("user/findAuth")
    Observable<BaseData> findAuth(@Body User user);

    /**
     * {mail,pwd,authCode}
     */
    //忘记密码修改
    @POST("user/find")
    Observable<BaseData> findByAuth(@Body User user);

    //修改密码
    @PUT("user/")
    Observable<TokenResult> changePwd(@Body String mail, @Body String oldPwd,
            @Body String newPwd);

    @GET("user/info/")
    Observable<UserInfoResult> getUserInfo();

    @PUT("user/info/")
    Observable<BaseData> updateUserInfo(@Body UserInfo userInfo);

    //key为要上传的文件名 格式为：avator/usermail.png
    @GET("http://182.254.247.206:8400/jobsapp/token")
    Observable<Response<TokenResult>> getUploadToken(@Header("key") String key,
            @Header("isFirst") String isFirst);

    //更新的时候删除用户之前的头像
    @DELETE("182.254.247.206:8400/jobsapp/{usermail}")
    Observable<Response> deleteAvator(
            @Path("usermail") String mail);

    //收藏版块
    //获取收藏列表
    @GET("collection/")
    Observable<CollectionResult> getCollections();

    @POST("collection/")
    Observable<BaseData> addCollection(@Body Collection collection);

    @DELETE("collection/{id}/")
    Observable<BaseData> removeCollection(@Path("id") long id);

    @POST("posts")
    Observable<BaseData> newPost(@Body PostContent postContent);

    @PUT("posts/{pid}")
    Observable<BaseData> changePost(@Path("pid") int pid, @Body PostContent postContent);

    @DELETE("posts/{pid}")
    Observable<BaseData> deletePost(@Path("pid") int pid);

    @POST("posts/{pid}/reply")
    Observable<BaseData> replyPost(@Body ReplyBean replyBean, @Path("pid") int pid);

    @DELETE("posts/{pid}/reply/{rid}")
    Observable<BaseData> deletePostReply(@Path("pid") int pid, @Path("rid") int rid);

    @GET("posts")
    Observable<PostListResult> getPostList();

    @GET("posts/{pid}")
    Observable<PostDetailResult> getPostDetail(@Path("pid") int pid);

    @POST("posts/collections/{pid}")
    Observable<BaseData> collectPost(@Path("pid") int pid,@Body PostData postData);

    @DELETE("posts/collections/{pid}")
    Observable<BaseData> deletePostCollection(@Path("pid")int pid);

    @GET("posts/collections")
    Observable<PostListResult> getPostCollections();

}
