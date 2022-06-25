package com.bobo.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bobo.common.base.BaseViewModel
import com.bobo.course.net.CourseCategoryRsp
import com.bobo.course.repo.ICourseResource
import com.bobo.course.ui.CoursePageAdapter

/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions:
 */
class CourseViewModel(private val repo: ICourseResource) : BaseViewModel() {

    // 课程分类
    val liveStudyInfo: LiveData<CourseCategoryRsp> = repo.liveCourseType

    val adapter = CoursePageAdapter()

    fun getCourseTypeList() = serverAwait {
        repo.getCourseTypeList()
    }

    suspend fun typedCourseList() =
        repo.getTypeCourseList()
            .asLiveData(viewModelScope.coroutineContext)
            .cachedIn(viewModelScope)
}