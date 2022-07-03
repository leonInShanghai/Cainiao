package com.bobo.home

import com.bobo.common.network.KtRetrofit
import com.bobo.common.utils.getBaseHost
import com.bobo.home.net.HomeService
import com.bobo.home.repo.HomeResource
import com.bobo.home.repo.IHomeResource
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * 依赖注入管理 Home的module
 */
val moduleHome = module {

    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(HomeService::class.java)
    }

    // repo IMineResource
    single { HomeResource(get()) } bind IHomeResource::class

    viewModel { HomeViewModel(get()) }

}