package com.bobo.service.assistant

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions: 配置dokit的工具类
 */

object AssistantApp {

    fun initConfig(application: Application){
        DoraemonKit.install(
            application, mutableListOf(
                ServiceHostKit()
            )
        )
    }

}