package com.bobo.course.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bobo.course.net.CourseCategoryRsp
import com.bobo.course.net.CourseDetails
import com.bobo.course.net.CourseListRsp
import com.bobo.course.net.MyCourseDetail
import kotlinx.coroutines.flow.Flow


/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions:
 */
interface ICourseResource {

    val liveCourseType: LiveData<CourseCategoryRsp>
    // 课程详情
    val liveCourseDetails: LiveData<CourseDetails?>

    /**s
     * 课程分类列表
     */
    suspend fun getCourseTypeList()

    /**
     * 根据类别获取课程列表
     */
    suspend fun getTypeCourseList(
        course_type: Int,
        code: String,
        difficuly: Int,
        is_free: Int,
        q: Int
    ): Flow<PagingData<CourseListRsp.Data>>

//    suspend fun getTypeCourseList(
//    ): Flow<PagingData<CourseListRsp.Data>>

    // 课程播放目录列表
    suspend fun getCourseDetails(
        course_id: Int
    )
}