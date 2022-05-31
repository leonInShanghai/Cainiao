package com.bobo.cainiao

import com.alibaba.android.arouter.launcher.ARouter
import com.bobo.common.BaseApplication
import com.bobo.common.ktx.application
import com.bobo.service.assistant.AssistantApp

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions: 全局应用程序类
 */
class CnApplication : BaseApplication() {

    override fun initConfig() {
        super.initConfig()

        // 添加common模块之外的其他模块，组件的koin的modules
        // loadKoinModules(modules)

        // doKit的初始化配置
        AssistantApp.initConfig(application)

        // 初始化Arouter框架
        ARouter.init(application)
    }

}