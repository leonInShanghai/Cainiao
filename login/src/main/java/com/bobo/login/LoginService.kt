package com.bobo.login

import com.bobo.login.net.LoginReqBody
import com.bobo.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions: 登录模块的接口
 */
interface LoginService {

    // 校验手机号是否注册
    // 挂起函数只能在挂起函数或协程内调用
    @GET("accounts/phone/is/register")
    fun isRegister(@Query("mobi") mobi: String): Call<BaseCniaoRsp>

    // 登录
    @POST("accounts/course/10301/login")
    fun login(@Body reqBody: LoginReqBody): Call<BaseCniaoRsp>

}