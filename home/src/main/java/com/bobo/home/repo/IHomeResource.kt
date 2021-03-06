package com.bobo.home.repo

import androidx.lifecycle.LiveData
import com.bobo.common.model.DataResult
import com.bobo.home.net.BannerList
import com.bobo.home.net.HomeList
import com.bobo.service.network.BaseResponse

/**
 * Created by 公众号：IT波 on 2022/7/3 Copyright © Leon. All rights reserved.
 * Functions:
 */
interface IHomeResource {

    // 首页上方banner图
    val liveBanner: LiveData<BannerList>

    suspend fun getBanner()


    // 首页模块名字、请求地址列表
    val liveHomeList: LiveData<HomeList>

    suspend fun getHomeList()


    // suspend fun getModuleDatas(moduleid: Int): DataResult<BaseResponse> //返回完整的数据

    suspend fun getJobDatas(moduleid: Int): DataResult<BaseResponse>  // 就业班

    suspend fun getHomeCourse(courseid: Int): DataResult<BaseResponse> // 课程推荐

    suspend fun getTeacherList(page: Int, size: Int):DataResult<BaseResponse> // 金牌讲师
}