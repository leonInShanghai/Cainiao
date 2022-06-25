package com.bobo.course.net

import com.bobo.service.network.BaseCniaoRsp
import com.bobo.service.network.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions:
 */
interface CourseService {

    /**
     * 课程分类
     */
    @GET("course/category")
    fun getCourseCategory(): Call<BaseResponse> // BaseCniaoRsp

    /**
     * 课程列表v2，根据code传参，分类python，Java，android等类别下的课程
     */
    @GET("course/v2/list")
    fun getCourseList(
        @Query("course_type") course_tyoe: Int = -1,
        @Query("code") code: String = "all",
        @Query("difficulty") difficulty: Int = -1,
        @Query("is_free") is_free: Int = -1,
        @Query("q") q: Int = -1,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ) : Call<BaseResponse> // BaseCniaoRsp
}