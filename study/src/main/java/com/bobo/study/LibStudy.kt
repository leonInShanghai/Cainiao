package com.bobo.study

import com.bobo.common.network.KtRetrofit
import com.bobo.common.utils.getBaseHost
import com.bobo.study.net.StudyService
import com.bobo.study.repo.IStudyResource
import com.bobo.study.repo.StudyRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by 公众号：IT波 on 2022/6/18 Copyright © Leon. All rights reserved.
 * Functions: Koin Kotlin 的依赖注入框架 的 study model
 */

val moduleStudy = module {

    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(StudyService::class.java)
    }

    // repo 2022/6/18 第一种写法
    single { StudyRepo( get<StudyService>()) } bind IStudyResource::class

    // single { StudyRepo(get()) } bind IStudyResource::class

    viewModel { StudyViewModel(get()) }
}