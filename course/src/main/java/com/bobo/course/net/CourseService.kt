package com.bobo.course.net

import com.bobo.service.network.BaseCniaoRsp
import com.bobo.service.network.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions: api地址：http://yapi.54yct.com/project/24/interface/api/2127
 */
interface CourseService {

    /**
     * 课程分类
     */
    @GET("course/category")
    fun getCourseCategory(): Call<BaseResponse> // BaseCniaoRsp

    /**
     * 课程列表v2，根据code传参，分类python，Java，android等类别下的课程
     * http://yapi.54yct.com/project/24/interface/api/2092
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

    /**
     * 获取课程播放目录详细列表
     * http://yapi.54yct.com/project/24/interface/api/2112
     * http://yapi.54yct.com/mock/24/course/detail?course_id=10012
     */
    @GET("course/chapter")
    fun getCourseDetails(
        @Query("course_id") courseid: Int //课程ID
    ): Call<BaseResponse>

    /**
     * <<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-
    响应 protocol:http/1.1 code: message:OK
    响应 request Url: http://yapi.54yct.com/mock/24/course/chapter?course_id=10224
    响应 sentRequestTime:2022-07-10 08:16:16 receivedResponseTime:2022-07-10 08:16:16
    响应 Header:{Date=Sun, 10 Jul 2022 08:16:17 GMT}
    , 响应 Header:{Content-Type=application/json; charset=utf-8}
    , 响应 Header:{Content-Length=151}
    , 响应 Header:{Connection=keep-alive}
    , 响应 Header:{Keep-Alive=timeout=60}
    , 响应 Header:{Access-Control-Allow-Origin=undefined}
    , 响应 Header:{Access-Control-Allow-Credentials=true}

    {"errcode":404,"errmsg":"不存在的api, 当前请求path为 /course/chapter， 请求方法为 GET ，请确认是否定义此请求。","data":null}
    <<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<
     */
}