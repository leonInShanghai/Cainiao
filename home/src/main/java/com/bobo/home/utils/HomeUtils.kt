package com.bobo.home.utils

import com.bobo.home.net.HomeCourseItem


/**
 * Created by 公众号：IT波 on 2022/7/3 Copyright © Leon. All rights reserved.
 * Functions: 用于单位转换 home专用
 */
object HomeUtils {

    @JvmStatic
    fun parseStudentComment(info: HomeCourseItem?): String {
        return "${info?.lessonsCount} ${info?.commentCount}人评价"
    }

    /**
     * 是否免费 免费返回true 否则返回false
     */
    @JvmStatic
    fun parseFree(info: HomeCourseItem?): Boolean {
        return info?.isFree == 1
    }

}