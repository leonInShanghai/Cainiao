package com.bobo.common

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by 公众号：IT波 on 2022/6/5 Copyright © Leon. All rights reserved.
 * Functions:
 */
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR) // log level Error 方能保证这句话不会报错，要么就不写这个

            androidContext(this@BaseApplication)

//            modules()
        }

        initConfig()
        initData()

        LogUtils.d("BaseApplication onCreate")
    }

    /**
     * 用于子类实现必要的数据初始化
     */
    protected open fun initConfig() {  }

    /**
     * 可用于子类实现必要的数据初始化
     */
    protected open fun initData() { }
}