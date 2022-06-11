package com.bobo.mine

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by 公众号：IT波 on 2022/6/11 Copyright © Leon. All rights reserved.
 * Functions: Koin Kotlin 的依赖注入框架 的 mine model
 */
val moduleMine = module {
    viewModel { MineViewModel() }
}