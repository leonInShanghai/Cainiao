package com.bobo.study.utils

/**
 * Created by 公众号：IT波 on 2022/6/19 Copyright © Leon. All rights reserved.
 * Functions:
 */
object StudyUtils {

    @JvmStatic
    fun rankStr(rank: Int) : String {
        return if (rank > 0) "第${rank}名" else "千里之外"
    }

}