package com.bobo.mine

import androidx.lifecycle.MutableLiveData
import com.bobo.common.base.BaseViewModel
import com.bobo.mine.repo.IMineResource
import com.bobo.service.repo.CniaoUserInfo

/**
 * Created by 公众号：IT波 on 2022/6/11 Copyright © Leon. All rights reserved.
 * Functions: api地址：http://yapi.54yct.com/project/24/interface/api/2127
 */
class MineViewModel(private val repo: IMineResource) : BaseViewModel() {

    // val liveUser = MutableLiveData<CniaoUserInfo>()

    // 用在userInfoFragment中
    val liveinfo = repo.liveUserInfo

    /**
     * 获取用户的信息
     */
    fun getUserInfo(token: String?) {
        serverAwait {
            repo.getUserInfo(token)
        }
    }
}