package com.bobo.course.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bobo.course.net.CourseCategoryRsp
import com.bobo.course.net.CourseListRsp
import kotlinx.coroutines.flow.Flow


/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions:
 */
interface ICourseResource {

    val liveCourseType: LiveData<CourseCategoryRsp>

    /**
     * 课程分类列表
     */
    suspend fun getCourseTypeList()

    /**
     * 根据类别获取课程列表
     */
    suspend fun getTypeCourseList(): Flow<PagingData<CourseListRsp.Data>>

}