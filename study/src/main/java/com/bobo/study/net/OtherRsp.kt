package com.bobo.study.net

import androidx.annotation.Keep

/**
 * Created by 公众号：IT波 on 2022/6/18 Copyright © Leon. All rights reserved.
 * Functions: 演示接口用的数据对象
 */

// banner
class BannerListRsp : ArrayList<BannerItem>()

@Keep
data class BannerItem(
    val client_url: String?,
    val created_time: String?,
    val id: Int,
    val img_url: String,
    val order_num: Int,
    val page_show: Int,
    val redirect_url: String?,
    val state: Int,
    val type: String?
)


@Keep
data class TeacherListMap(
    val datas: List<Teacher>?,
    val page: Int,
    val size: Int,
    val total: Int,
    val  total_page: Int
) {
    @Keep
    data class Teacher(
        val brief: String?,
        val company: String?,
        val id: Int,
        val job_title: String?,
        val logo_url: String?,
        val teacher_name:String?
    )
}
