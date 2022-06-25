package com.bobo.course.repo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.blankj.utilcode.util.LogUtils
import com.bobo.common.network.support.serverData
import com.bobo.course.net.CourseListRsp
import com.bobo.course.net.CourseService
import com.bobo.service.network.onBizError
import com.bobo.service.network.onBizOK
import com.bobo.service.network.onFailure
import com.bobo.service.network.onSuccess


/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions:
 */
class CoursePagingSource(
    private val service: CourseService,
    private val course_type: Int = -1,
    private val code: String = "all",
    private val difficulty: Int = -1, // 难度
    private val is_free: Int = -1,
    private val q: Int = -1 // 排序
) : PagingSource<Int, CourseListRsp.Data>() {

    /*
    * 该办法只在初始加载成功且加载页面的列表不为空的情况下被调用
    *
    * 如果您的应用程序需要支持从网络增量加载到本地数据库，则必须为从用户的滚动位置锚点开始的恢复分页提供支持
    * 先从本地数据库加载数据，然后在数据库用完数据后从网络加载数据
    * */
    override fun getRefreshKey(state: PagingState<Int, CourseListRsp.Data>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseListRsp.Data> {
        var result: LoadResult<Int, CourseListRsp.Data> = LoadResult.Error(Exception("加载中..."))

        val pageNum = params.key ?: 1
        service.getCourseList(course_type, code, difficulty, is_free, 1, pageNum, params.loadSize)
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("获取type类型的课程列表 onBizError $code, $message")
                    result = LoadResult.Error(Exception(message))
                }
                onBizOK<CourseListRsp> { code, data, message ->
                    LogUtils.i("获取type类型的课程列表 onBizOK $data")
                    val totalPage = data?.totalPage?:0
                    result = LoadResult.Page(
                        data = data?.datas ?: emptyList(),
                        prevKey = if (pageNum == 1) null else pageNum - 1,
                        nextKey = if (pageNum < totalPage) pageNum + 1 else null
                    )
                }
            }.onFailure {
                LogUtils.e("获取type类型的课程列表 onFailure ${it.message} ")
                result = LoadResult.Error(it)
            }

        return result
    }
}