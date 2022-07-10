package com.bobo.course.net


import com.google.gson.annotations.SerializedName

data class Authority(@SerializedName("user_type")
                     val userType: String = "",
                     @SerializedName("has_authority")
                     val hasAuthority: Boolean = false,
                     @SerializedName("authority_type")
                     val authorityType: Int = 0,
                     @SerializedName("days")
                     val days: Int = 0)


data class Teacher(@SerializedName("brief")
                   val brief: String = "",
                   @SerializedName("teacher_name")
                   val teacherName: String = "",
                   @SerializedName("logo_url")
                   val logoUrl: String = "",
                   @SerializedName("company")
                   val company: String = "",
                   @SerializedName("id")
                   val id: Int = 0,
                   @SerializedName("job_title")
                   val jobTitle: String = "")


data class MyCourseDetail(@SerializedName("code")
                          val code: Int = 0,
                          @SerializedName("data")
                          val data: Data,
                          @SerializedName("message")
                          val message: String = "")


data class Data(@SerializedName("comment_count")
                val commentCount: Int = 0,
                @SerializedName("lessons_finished_count")
                val lessonsFinishedCount: Int = 0,
                @SerializedName("is_pt")
                val isPt: Boolean = false,
                @SerializedName("h5site")
                val hSite: Any? = null,
                @SerializedName("expiry_day")
                val expiryDay: Int = 0,
                @SerializedName("recommend_count")
                val recommendCount: Int = 0,
                @SerializedName("first_category")
                val firstCategory: FirstCategory,
                @SerializedName("course_type")
                val courseType: Int = 0,
                @SerializedName("teacher")
                val teacher: Teacher,
                @SerializedName("can_use_coupon")
                val canUseCoupon: Int = 0,
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("can_buy")
                val canBuy: Int = 0,
                @SerializedName("cost_price")
                val costPrice: Int = 0,
                @SerializedName("brief")
                val brief: String = "",
                @SerializedName("created_time")
                val createdTime: String = "",
                @SerializedName("website")
                val website: Any? = null,
                @SerializedName("goal")
                val goal: Any? = null,
                @SerializedName("sub_title")
                val subTitle: Any? = null,
                @SerializedName("has_authority")
                val hasAuthority: Boolean = false,
                @SerializedName("fit_to")
                val fitTo: Any? = null,
                @SerializedName("pre_tech")
                val preTech: Any? = null,
                @SerializedName("now_price")
                val nowPrice: Int = 0,
                @SerializedName("qr_img_url")
                val qrImgUrl: String = "",
                @SerializedName("class_difficulty")
                val classDifficulty: Int = 0,
                @SerializedName("img_url")
                val imgUrl: String = "",
                @SerializedName("authority")
                val authority: Authority,
                @SerializedName("is_free")
                val isFree: Int = 0,
                @SerializedName("lessons_time")
                val lessonsTime: Int = 0,
                @SerializedName("is_live")
                val isLive: Int = 0,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("lessons_played_time")
                val lessonsPlayedTime: Int = 0,
                @SerializedName("is_distribution")
                val isDistribution: Boolean = false,
                @SerializedName("desc")
                val desc: String = "",
                @SerializedName("lessons_count")
                val lessonsCount: Int = 0)


data class FirstCategory(@SerializedName("code")
                         val code: String = "",
                         @SerializedName("id")
                         val id: Int = 0,
                         @SerializedName("title")
                         val title: String = "")


