package com.bobo.study.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.bobo.study.net.BoughtRsp
import com.bobo.study.net.StudiedRsp
import com.bobo.study.net.StudyInfoRsp
import kotlinx.coroutines.flow.Flow

/**
 * Created by 公众号：IT波 on 2022/6/18 Copyright © Leon. All rights reserved.
 * Functions: 学习中心网络请求接口
 */
interface IStudyResource {

    val liveStudyInfo: LiveData<StudyInfoRsp>
    val liveStudyList: LiveData<StudiedRsp>
    val liveBoughtList: LiveData<BoughtRsp>

    /**
     * 学习情况
     */
    suspend fun getStudyInfo()

    /**
     * 最近学习列表
     */
    suspend fun getStudyList()

    /**
     * 购买的课程
     */
    suspend fun getBoughtCourse()

    suspend fun pagingData(): Flow<PagingData<StudiedRsp.Data>>
}