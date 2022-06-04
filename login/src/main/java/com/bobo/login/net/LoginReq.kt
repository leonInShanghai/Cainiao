package com.bobo.login.net

import androidx.annotation.Keep
import com.bobo.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions:
 */

//@Keep
//data class LoginReqBody (
//    val mobi: String, // 手机号
//    val password: String // 密码
//)

@Keep
data class LoginReqBody (
    val mobi: String, // 手机号
    val password: String // 密码
    )

