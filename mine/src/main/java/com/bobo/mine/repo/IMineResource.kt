package com.bobo.mine.repo

import androidx.lifecycle.LiveData
import com.bobo.mine.net.UserInfoRsp

/**
 * Created by 公众号：IT波 on 2022/6/12 Copyright © Leon. All rights reserved.
 * Functions: 我的模块用户信息接口
 */
interface IMineResource {

    /**
     * 用户信息的返回数据类 livedata
     */
    val liveUserInfo: LiveData<UserInfoRsp>

    /**
     * 获取UserInfo的api数据
     */
    suspend fun getUserInfo(token: String?)
}