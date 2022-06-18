package com.bobo.service

import com.bobo.common.network.KtRetrofit
import org.koin.dsl.module

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions:
 */

val moduleService = module {
    // host作为外部参数传进来给KtRetrofit进行初始化，得到ktretrofit单例类对象 给debug第三方库 https://www.dokit.cn/用的
    single<KtRetrofit> { (host: String) -> KtRetrofit.initConfig(host) }

    // June 12 add(不写这个目前也能正常运行)
    // single {
    //     KtRetrofit.initConfig("http://yapi.54yct.com/mock/24/")
    // }

}