package com.bobo.login

import com.bobo.login.net.LoginService
import com.bobo.login.repo.ILoginResource
import com.bobo.login.repo.LoginRepo
import com.bobo.common.network.KtRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by 公众号：IT波 on 2022/6/2 Copyright © Leon. All rights reserved.
 * Functions: koin注解管理类
 */
val moduleLogin: Module = module {

    // service retrofit
    // single声明单例对象
     single {
         // http://yapi.54yct.com/mock/24/
         KtRetrofit.initConfig("http://yapi.54yct.com/mock/24/") //baseurl
             .getService(LoginService::class.java)
     }

    // single {
    //     get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(LoginService::class.java)
    // }

    // repo ILoginResource
    single { LoginRepo(get()) } bind ILoginResource::class

    // viewModel
    viewModel { LoginViewModel(get()) }
}

//package com.bobo.login
//
//import com.bobo.common.network.KtRetrofit
//import com.bobo.login.net.LoginService
//import com.bobo.login.repo.ILoginResource
//import com.bobo.login.repo.LoginRepo
//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.core.module.Module
//import org.koin.dsl.bind
//import org.koin.dsl.module
//
///*
//* koin注解管理类
//* */
//val moduleLogin: Module = module {
//
//    // Service retrofit  single声明单例对象的实例化方式
//    single {
//        KtRetrofit.initConfig("http://v.juhe.cn/chengyu/query?key=fe11d8f855264b7ce" +
//                "8c6a4ba026f9b71&word=%E4%B8%80%E5%BF%83%E4%B8%80%E6%84%8F").getService(LoginService::class.java)
//    }
//
//    // repo LoginResource
//    single { LoginRepo(get()) } bind ILoginResource::class
//
//    // viewModel
//    viewModel { LoginViewModel(get())  }
//
//
////    single {
////        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(LoginService::class.java)
////    }
////
////    //repo ILoginResource
////    single { LoginRepo(get()) } bind ILoginResource::class
////
////    //viewModel
////    viewModel { LoginViewModel(get()) }
//}