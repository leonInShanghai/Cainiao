package com.bobo.mine.net

import com.bobo.service.network.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by 公众号：IT波 on 2022/6/12 Copyright © Leon. All rights reserved.
 * Functions: 我的模块联网请求接口
 */
interface MineService {

    /**
     * 用户详情的获取 /member/userinfo
     */
    @GET("member/userinfo")
    fun getUserInfo(@Header("token") token: String?) : Call<BaseResponse> // BaseCniaoRsp
}