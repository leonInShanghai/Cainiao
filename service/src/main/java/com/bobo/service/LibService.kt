package com.bobo.service

import org.koin.dsl.module

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions:
 */

val moduleService = module {
    // host作为外部参数传进来给KtRetrofit进行初始化，得到ktretrofit单例类对象
    //single<KtRetrofit> { (host: String) -> KtRetrofit.initConfig(host) }
}