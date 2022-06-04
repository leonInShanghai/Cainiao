package com.bobo.login

import com.bobo.common.BaseApplication
import com.bobo.service.moduleService
import org.koin.core.context.loadKoinModules

/**
 * Created by 公众号：IT波 on 2022/6/2 Copyright © Leon. All rights reserved.
 * Functions: 登录模块的Application
 */
class LoginApplication: BaseApplication() {

    override fun initConfig() {
        super.initConfig()
        loadKoinModules(moduleService)
        loadKoinModules(moduleLogin)
    }
}