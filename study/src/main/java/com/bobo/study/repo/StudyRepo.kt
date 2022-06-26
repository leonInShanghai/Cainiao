package com.bobo.study.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.blankj.utilcode.util.LogUtils
import com.bobo.common.network.support.serverData
import com.bobo.common.utils.getBaseHost
import com.bobo.service.network.onBizError
import com.bobo.service.network.onBizOK
import com.bobo.service.network.onFailure
import com.bobo.service.network.onSuccess
import com.bobo.study.net.BoughtRsp
import com.bobo.study.net.StudiedRsp
import com.bobo.study.net.StudyInfoRsp
import com.bobo.study.net.StudyService
import kotlinx.coroutines.flow.Flow

/**
 * Created by 公众号：IT波 on 2022/6/18 Copyright © Leon. All rights reserved.
 * Functions:
 */
class StudyRepo(private val service: StudyService) : IStudyResource {

    private val _studyInfo = MutableLiveData<StudyInfoRsp>()
    private val _seudyList = MutableLiveData<StudiedRsp>()
    private val _boughtList = MutableLiveData<BoughtRsp>()

    override val liveStudyInfo: LiveData<StudyInfoRsp> = _studyInfo
    override val liveStudyList: LiveData<StudiedRsp> = _seudyList
    override val liveBoughtList: LiveData<BoughtRsp> = _boughtList

    override suspend fun getStudyInfo() {
        service.getStudyInfo()
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("获取学习信息 BizError $code, $message")
                }
                onBizOK<StudyInfoRsp> { code, data, message ->
                    _studyInfo.value = data
                    LogUtils.i("获取学习信息 BizOK $data")
                    return@onBizOK
                }

            }.onFailure {
                LogUtils.e("获取学习信息 接口异常 ${it.message}")
            }
    }

    override suspend fun getStudyList() {
        service.getStudyList()
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("获取学习过的课程列表 onBizError $data, $message")
                }
                onBizOK<StudiedRsp> { code, data, message ->
                    _seudyList.value = data?.apply {
                        // 已经在 service\utils\BindingApt.kt 处理
                        // datas?.forEach {
                        //     if (it.imgUrl?.startsWith("/") == true) {
                        //         it.imgUrl = "${getBaseHost()}${it.imgUrl}"
                        //     }
                        // }
                    }
                    LogUtils.i("获取学习过的课程列表 onBizOK $data")
                    return@onBizOK
                }
            }
            .onFailure {
                LogUtils.e("获取学习过的课程列表 Failure ${it.message}")
            }
    }

    override suspend fun getBoughtCourse() {
        service.getBoughtCourse().serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("获取购买课程 onBizError $code, $message")
                }
                onBizOK<BoughtRsp> { code, data, message ->
                    _boughtList.value = data
                    LogUtils.i("获取购买课程 onBizOK $data")
                    return@onBizOK
                }
            }
            .onFailure {
                LogUtils.e("获取购买课程 onFailure ${it.message}")
            }
    }

    private val pageSize = 100
    override suspend fun pagingData(): Flow<PagingData<StudiedRsp.Data>> {
        val config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = 5,
            initialLoadSize = 10,
            maxSize = pageSize * 3
        )
        return Pager(config = config, null) {
            StudyItemPagingSource(service)
        }.flow
    }
}

class StudyItemPagingSource(private val service: StudyService) : PagingSource<Int, StudiedRsp.Data>() {

    /*
    * 该办法只在初始加载成功且加载页面的列表不为空的情况下被调用
    *
    * 如果您的应用程序需要支持从网络增量加载到本地数据库，则必须为从用户的滚动位置锚点开始的恢复分页提供支持
    * 先从本地数据库加载数据，然后在数据库用完数据后从网络加载数据
    * */
    override fun getRefreshKey(state: PagingState<Int, StudiedRsp.Data>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StudiedRsp.Data> {
       var result : LoadResult<Int, StudiedRsp.Data> = LoadResult.Error<Int, StudiedRsp.Data>(Exception("加载中"))
       val firstPage = params.key ?: 1
        var nextPageNum = firstPage + 1
        service.getStudyList(page = firstPage, size = params.loadSize)
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("加载更多 获取学过的课程列表 BizError $code, $message")
                    result = LoadResult.Error<Int, StudiedRsp.Data>(Exception(message))
                }
                onBizOK<StudiedRsp> { code, data, message ->
                    LogUtils.i("加载更多 获取学过的课程列表 onBizOK $data")
                    val totalPage = data?.totalPage ?: 0
                    if (nextPageNum < totalPage) {
                            nextPageNum++
                    }
                    result = LoadResult.Page<Int, StudiedRsp.Data>(
                        data = data?.datas ?: emptyList(),
                        prevKey = null,
                        nextKey = nextPageNum
                    )
                }
            }
            .onFailure {
                LogUtils.e("加载更多 获取学过的课程列表 onFailure ${it.message}")
                result = LoadResult.Error<Int, StudiedRsp.Data>(it)
            }
        return result
    }
}