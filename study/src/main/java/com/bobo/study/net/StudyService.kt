package com.bobo.study.net

import com.bobo.service.network.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by 公众号：IT波 on 2022/6/18 Copyright © Leon. All rights reserved.
 * Functions: 修正：BaseCniaoRsp ->  BaseResponse
 */
interface StudyService {

    /**
     * 用户学习详情
     * FIXME:注意baseUrl最后有 "/" 所以相对路径前面不要再加了
     */
    @GET("member/study/info")
    fun getStudyInfo(): Call<BaseResponse>

    /**
     * 用户学习的课程列表
     * FIXME:注意baseUrl最后有 "/" 所以相对路径前面不要再加了
     */
    @GET("member/courses/studied")
    fun getStudyList(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ) : Call<BaseResponse>

    /**
     * 用户购买的课程
     * 同样有page和size，默认 1 和 10
     * FIXME:注意baseUrl最后有 "/" 所以相对路径前面不要再加了
     */
    @GET("member/courses/bought")
    fun getBoughtCourse(
        @Query("page") page:Int = 1,
        @Query("size") size: Int = 10
    ) : Call<BaseResponse>
}