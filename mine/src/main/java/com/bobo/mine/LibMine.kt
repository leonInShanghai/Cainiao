package com.bobo.mine

import com.bobo.common.network.KtRetrofit
import com.bobo.mine.net.MineService
import com.bobo.mine.repo.IMineResource
import com.bobo.mine.repo.MineRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by 公众号：IT波 on 2022/6/11 Copyright © Leon. All rights reserved.
 * Functions: Koin Kotlin 的依赖注入框架 的 mine model
 */
val moduleMine = module {

    single {
        KtRetrofit.initConfig("http://yapi.54yct.com/mock/24/")
            .getService(MineService::class.java)
    }

    single { MineRepo(get()) } bind IMineResource::class

    viewModel { MineViewModel(get()) }
}