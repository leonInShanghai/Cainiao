package com.bobo.mine.net


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//可序列化的数据类型,必须保证里面的变量都是可序列化的
 @Keep
 @Parcelize
 data class UserInfoRsp(
     var company: String?, //公司
     val desc: String?, //个人介绍
     val email: String?,
     @SerializedName("focus_it")
     val focusIt: String?,
     @SerializedName("follower_count")
     val followerCount: Int,
     @SerializedName("following_count")
     val followingCount: Int,
     val id: Int,
     val job: String?, //职业
     @SerializedName("logo_url")
     var logoUrl: String?, //头像url
     val mobi: String?, //手机号
     @SerializedName("real_name")
     val realName: String?, //真实姓名
     val username: String?, //用户名
     @SerializedName("work_years")
     val workYears: String? //工作时间
 ) : Parcelable


/**
 * Created by 公众号：IT波 on 2022/6/12 Copyright © Leon. All rights reserved.
 * Functions: 使用Generate class from json 一键生成bean对象
 */
//data class UserInfoRsp(@SerializedName("logo_url")
//                val logoUrl: String = "",
//                @SerializedName("real_name")
//                val realName: String = "",
//                @SerializedName("follower_count")
//                val followerCount: Int = 0,
//                @SerializedName("work_years")
//                val workYears: String = "",
//                @SerializedName("focus_it")
//                val focusIt: String = "",
//                @SerializedName("following_count")
//                val followingCount: Int = 0,
//                @SerializedName("company")
//                var company: String = "",
//                @SerializedName("mobi")
//                val mobi: String = "",
//                @SerializedName("id")
//                val id: Int = 0,
//                @SerializedName("job")
//                val job: String = "",
//                @SerializedName("email")
//                val email: String = "",
//                @SerializedName("desc")
//                val desc: String = "",
//                @SerializedName("username")
//                val username: String = "")




