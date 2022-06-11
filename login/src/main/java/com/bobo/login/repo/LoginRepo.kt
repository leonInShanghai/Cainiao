package com.bobo.login.repo

import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.LogUtils
import com.bobo.common.model.SingleLiveData
import com.bobo.common.network.support.serverData
import com.bobo.login.net.LoginService
import com.bobo.login.net.LoginReqBody
import com.bobo.login.net.LoginRsp
import com.bobo.login.net.RegisterRsp
import com.bobo.service.network.onBizError
import com.bobo.service.network.onBizOK
import com.bobo.service.network.onFailure
import com.bobo.service.network.onSuccess
import com.bobo.service.repo.CniaoUserInfo
import retrofit2.await


/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions:
 */
class LoginRepo(private val service: LoginService): ILoginResource {


    private val _registerRsp = SingleLiveData<RegisterRsp>()
    private val _loginRsp = SingleLiveData<LoginRsp>()

    override val registerRsp: LiveData<RegisterRsp> = _registerRsp
    override val loginRsp: LiveData<LoginRsp> = _loginRsp

    override suspend fun checkRegister(mobi: String) {
        // val serverData = service.isRegister(mobi)
        service.isRegister(mobi)
            .serverData()
            .onSuccess {
                // 只要不是接口响应成功
                onBizError { code, message ->
                    LogUtils.w("是否注册 BizError $code, $message")
                }

                onBizOK<RegisterRsp> { code, data, message ->
                    _registerRsp.value = data
                    LogUtils.i("是否注册 BizOK $data")
                    return@onBizOK
                }
            }
            .onFailure {
                LogUtils.e("是否注册 接口异常 ${it.message}")
            }

    }

    override suspend fun requestLogin(body: LoginReqBody) {
        service.login(body)
            .serverData()
            .onSuccess {
                // 只要是不是接口响应成功
                onBizError { code, message ->
                    LogUtils.w("登录接口 BizeError $code, $message")
                }
                onBizOK<LoginRsp> { code, data, message ->

                    // region 自定义头像姓名-自己造数据
                    val user: CniaoUserInfo.User = CniaoUserInfo.User(data!!.user!!.id,
                        "http://wx.qlogo.cn/mmopen/licWm10icRyasaxnp0t7IdEVAvpag9KiaLCc2XZiacGygB06zBlw" +
                                "7eJF3icTeickalN0x3QLfOMDTq6V54p0gwtzl43GxF8LSVZHKC/64",
                        "2022-1-1",
                    "自己造数据")
                    val newData =  CniaoUserInfo(0, data!!.course_permission, data.token, user)
                    _loginRsp.value = newData
                    // endregion 自定义头像姓名-自己造数据

                    // 原来代码
                    // _loginRsp.value = data

                    LogUtils.i("登录接口 BizOK  $data")
                }
            }
            .onFailure {
                LogUtils.e("登录接口 异常 ${it.message}")
            }
    }
}