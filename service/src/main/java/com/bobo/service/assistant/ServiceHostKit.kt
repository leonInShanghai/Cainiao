package com.bobo.service.assistant

import android.content.Context
import com.bobo.service.R
import com.didichuxing.doraemonkit.kit.AbstractKit

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions: 用于配置不同的接口host，测试工具
 */
class ServiceHostKit: AbstractKit() {

    override val icon: Int
        get() = R.drawable.icon_server_host

    override val name: Int
        get() = R.string.str_server_host_dokit

    override fun onAppInit(context: Context?) {

    }

    override fun onClick(context: Context?) {

    }
}