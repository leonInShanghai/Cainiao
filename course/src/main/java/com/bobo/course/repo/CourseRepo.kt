package com.bobo.course.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blankj.utilcode.util.LogUtils
import com.bobo.common.network.support.serverData
import com.bobo.course.net.*
import com.bobo.course.repo.data.CoursePagingSource
import com.bobo.service.network.onBizError
import com.bobo.service.network.onBizOK
import com.bobo.service.network.onFailure
import com.bobo.service.network.onSuccess
import kotlinx.coroutines.flow.Flow

/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions:
 */
class CourseRepo(private val service: CourseService) : ICourseResource {

    private val _courseType = MutableLiveData<CourseCategoryRsp>()
    // liveCourseType
    override val liveCourseType: LiveData<CourseCategoryRsp> = _courseType

    // 课程播放目录列表
     private val _liveCourseDetails = MutableLiveData<CourseDetails?>()
     override val liveCourseDetails: LiveData<CourseDetails?> = _liveCourseDetails
//    private val _liveCourseDetails = MutableLiveData<MyCourseDetail?>()
//    override val liveCourseDetails: LiveData<MyCourseDetail?> = _liveCourseDetails

    override suspend fun getCourseTypeList() {
        service.getCourseCategory()
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("CourseRepo 获取课程分类 BizError $code, $message")
                }
                onBizOK<CourseCategoryRsp> { code, data, message ->
                    _courseType.value = data
                    LogUtils.w("CourseRepo 获取课程分类 onBizOK $data")
                }
            }.onFailure {
                LogUtils.e("CourseRepo 获取课程分类 onFailure ${it.message}")
            }
    }

    private val pageSize = 20
    override suspend fun getTypeCourseList(
        course_type: Int,
        code: String,
        difficuly: Int,
        is_free: Int,
        q: Int
    ): Flow<PagingData<CourseListRsp.Data>> {
        val config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = 5,
            initialLoadSize = 10,
            maxSize = pageSize * 3
        )

        return Pager(config = config, null) {
            CoursePagingSource(service, course_type, code, difficuly, is_free, q)
        }.flow
    }
//    override suspend fun getTypeCourseList(): Flow<PagingData<CourseListRsp.Data>> {
//        val config = PagingConfig(
//            pageSize = pageSize,
//            prefetchDistance = 5,
//            initialLoadSize = 10,
//            maxSize = pageSize * 3
//        )
//
//        return Pager(config = config, null) {
//            CoursePagingSource(service)
//        }.flow
//    }

    /**
     * 课程播放目录列表
     */
    override suspend fun getCourseDetails(course_id: Int) {
        service.getCourseDetails(course_id)
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("获取课程播放目录 BizError $code,$message")
                }
                // onBizOK<CourseDetails> { code, data, message ->
                onBizOK<CourseDetails> { code, data, message ->
                    _liveCourseDetails.value = data
                    LogUtils.i("获取课程播放目录 BizOK $data")
                }
            }
            .onFailure {
                LogUtils.e("获取课程播放目录 接口异常 ${it.message}")
            }
    }

}