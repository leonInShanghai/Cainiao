package com.bobo.study

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.bobo.common.base.BaseViewModel
import com.bobo.service.repo.CniaoUserInfo
import com.bobo.study.net.BoughtRsp
import com.bobo.study.net.StudiedRsp
import com.bobo.study.net.StudyInfoRsp
import com.bobo.study.repo.IStudyResource
import com.bobo.study.ui.StudyPageAdapter

/**
 * Created by 公众号：IT波 on 2022/6/18 Copyright © Leon. All rights reserved.
 * Functions:
 */
class StudyViewModel(private val repo: IStudyResource) : BaseViewModel() {

    // 学习页面的数据
    val liveStudyInfo: LiveData<StudyInfoRsp> = repo.liveStudyInfo
    val liveStudyList: LiveData<StudiedRsp> = repo.liveStudyList
    val liveBoughtList: LiveData<BoughtRsp> = repo.liveBoughtList

    // 用户基本信息，头像和名字
    val obUserInfo = ObservableField<CniaoUserInfo>()

    // 适配器
    val adapter = StudyPageAdapter()

    fun getStudyData() = serverAwait {
        repo.getStudyInfo()
        repo.getStudyList()
        repo.getBoughtCourse()
    }

    suspend fun pagingData() = repo.pagingData().asLiveData()
}