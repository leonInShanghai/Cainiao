package com.bobo.login.net

import androidx.annotation.Keep

/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions:
 */
@Keep
data class LoginRsp(
    val is_register: Int = FLAG_UN_REGISTERED // 1表示注册，0表示未注册
) {
    companion object {
        const val FLAG_IS_REGISTERED = 1 //已经注册
        const val FLAG_UN_REGISTERED = 0 //未注册
    }
}