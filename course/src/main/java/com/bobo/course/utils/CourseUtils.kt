package com.bobo.course.utils

import com.bobo.course.net.CourseListRsp

/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions: 课程工具类
 */
object CourseUtils {

    /*
   * 总课时 + 评价人数
   * */
    @JvmStatic
    fun parseStudentComment(info: CourseListRsp.Data?): String {
        return "${info?.lessonsCount}  ${info?.commentCount}人评价"
    }

    /*
    * 是否免费 免费返回true 否则返回false
    * */
    @JvmStatic
    fun parseFree(info: CourseListRsp.Data?): Boolean {
        return info?.isFree == 1
    }

}