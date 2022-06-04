package com.bobo.login

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.bobo.common.base.BaseViewModel
import com.bobo.login.net.LoginReqBody
import com.bobo.login.net.LoginRsp
import com.bobo.login.net.RegisterRsp
import com.bobo.login.repo.ILoginResource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

const val TAG = "LoginViewModel"

/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions: 登录界面的ViewModel
 */
class LoginViewModel(private val resource: ILoginResource): BaseViewModel() {

    // 登录账号密码Observable对象
    val obMobile = ObservableField<String>()
    val obPassword = ObservableField<String>()

    // 返回的数据
    val liveRegisterRsp: LiveData<RegisterRsp> = resource.registerRsp
    val liveLoginRsp: LiveData<LoginRsp> = resource.loginRsp

    /**
     * 检查账号是否注册
     */
    private fun checkRegister(mobi: String) = serverAwait {
        resource.checkRegister(mobi)
    }

    /**
     * 调用登录
     * 18648957777
     * cn5123456
     */
    internal fun repoLogin() {
        val account = obMobile.get() ?: return
        val password = obPassword.get() ?: return
        serverAwait {
            resource.requestLogin(LoginReqBody(account, password))
        }
    }

    /**
     * 调用登录，两步 1,判断手机号是否有注册 2,已经注册的去调用登录
     */
    fun goLogin() {
        // serverAwait {
        //     resource.checkRegister("18648957777")
        //     resource.requestLogin(LoginReqBody("18648957777", "cn5123456"))
        // }
        val account = obMobile.get() ?: return
        checkRegister(account)
    }

    fun wechat(context: Context) {
        Log.d(TAG, "点击了微信登陆 view id = ${context.packageName}")
        Toast.makeText(context, "微信登录功能没有开发", Toast.LENGTH_SHORT).show()
    }

    fun qq(view: View) {
        Log.d(TAG, "点击了QQ登录 view id = ${view.id}")
        Toast.makeText(view.context, "QQ登录功能没有开发", Toast.LENGTH_SHORT).show()
    }


    fun weibo() {
        Log.d(TAG, "点击了微博登录 没有 id")
        ToastUtils.showShort("微博登录功能没有开发")
    }

    fun AA(view: View) {
        Log.d(TAG, "静态点击方式 view id = ${view.id}")
        Toast.makeText(view.context, "忘记密码功能没有开发", Toast.LENGTH_SHORT).show()
    }
}

