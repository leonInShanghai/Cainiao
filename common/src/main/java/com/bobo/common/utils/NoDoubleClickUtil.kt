package com.bobo.common.utils

import kotlin.math.abs

/**
 * Created by 公众号：IT波 on 2022/7/16 Copyright © Leon. All rights reserved.
 * Functions: android连续点击出现多个Activity界面的解决方法
 */
object NoDoubleClickUtil {
    private var lastClickTime: Long = 0
    // kotlin 定义一个私有的常量
    private const val SPACE_TIME = 600

    fun initLastClickTime() {
        lastClickTime = 0
    }

    @Synchronized
    fun isDoubleClick(): Boolean {
        val currentTime = System.currentTimeMillis()
        val isClickDouble: Boolean = abs(currentTime - lastClickTime) <= SPACE_TIME
        lastClickTime = currentTime
        return isClickDouble
    }
}