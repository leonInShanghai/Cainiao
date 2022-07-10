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
 * Functions: api地址：http://yapi.54yct.com/project/24/interface/api/2127
 */
class CourseViewModel(private val repo: ICourseResource) : BaseViewModel() {

    // 课程分类 liveCourseType
    val liveTypes: LiveData<CourseCategoryRsp> = repo.liveCourseType

    val adapter = CoursePageAdapter()

    fun getCourseTypeList() = serverAwait {
        repo.getCourseTypeList()
    }

    suspend fun typedCourseList(
        course_type: Int = -1,
        code: String = "all",
        difficuly: Int = -1,
        is_free: Int = -1,
        q: Int = -1
    ) =
        repo.getTypeCourseList(course_type,
            code,
            difficuly,
            is_free,
            q).cachedIn(viewModelScope)  // 跟viewModel的生命周期绑定
            // .asLiveData(viewModelScope.coroutineContext)
            // .cachedIn(viewModelScope)

//    suspend fun typedCourseList() =
//        repo.getTypeCourseList()
//            .asLiveData(viewModelScope.coroutineContext)
//            .cachedIn(viewModelScope)

}