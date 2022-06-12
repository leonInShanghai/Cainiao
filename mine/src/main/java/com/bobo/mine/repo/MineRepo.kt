package com.bobo.mine.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.bobo.common.network.support.serverData
import com.bobo.mine.net.MineService
import com.bobo.mine.net.UserInfoRsp
import com.bobo.service.network.onBizError
import com.bobo.service.network.onBizOK
import com.bobo.service.network.onFailure
import com.bobo.service.network.onSuccess

/**
 * Created by 公众号：IT波 on 2022/6/12 Copyright © Leon. All rights reserved.
 * Functions:
 */
class MineRepo(private val service: MineService) : IMineResource {

    private val _userInfoRep = MutableLiveData<UserInfoRsp>()

    override val liveUserInfo: LiveData<UserInfoRsp> = _userInfoRep

    override suspend fun getUserInfo(token: String?) {
        service.getUserInfo(token)
            .serverData()
            .onSuccess {
                // 只要不是接口响应成功
                onBizError { code, message ->
                    LogUtils.w("获取用户信息 onBizError: $code, $message")
                }
                // 此时接口响应成功
                onBizOK<UserInfoRsp> { code, data, message ->

                    // region 自己修改头像url的拼接
                    data!!.logoUrl = "http:" + data.logoUrl
                    // endregion 自己修改头像url的拼接

                    _userInfoRep.value = data
                    LogUtils.i("获取用户信息 onBizOK: $data")
                    return@onBizOK
                }
            }
            .onFailure {
                LogUtils.e("获取用户信息异常: ${it.message}")
            }
    }
}