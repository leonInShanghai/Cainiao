package com.bobo.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions: 登录界面的ViewModel
 */
class LoginViewModel: ViewModel() {

    // 登录账号密码Observable对象
    val obMobile = ObservableField<String>()
    val obPassword = ObservableField<String>()

    fun goLogin() {

    }
}