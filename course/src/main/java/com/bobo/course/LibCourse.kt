package com.bobo.course

import com.bobo.common.network.KtRetrofit
import com.bobo.common.utils.getBaseHost
import com.bobo.course.net.CourseService
import com.bobo.course.repo.CourseRepo
import com.bobo.course.repo.ICourseResource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions:
 */
val moduleCourse = module{

    single {
        get<KtRetrofit> { parametersOf(getBaseHost())}.getService(CourseService::class.java)
    }

    single { CourseRepo(get()) } bind ICourseResource::class

    viewModel { CourseViewModel(get()) }

    // No definition found for class:'com.bobo.course.PlayVideoViewModel'. Check your definitions!
    viewModel { PlayVideoViewModel(get()) }
}