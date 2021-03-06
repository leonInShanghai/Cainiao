package com.bobo.service.utils

import android.widget.ImageView
import android.widget.TextView
import com.bobo.service.R
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

/**
 * Created by 公众号：IT波 on 2022/6/4 Copyright © Leon. All rights reserved.
 * Functions: 项目适配用的BindingAdapter
 */

/**
 * imageView支持图片加载 绑定
 * Application namespace for attribute app:srcCompat will be ignored. app:srcCompat -> srcCompat
 */
@BindingAdapter("srcCompat", requireAll = false)
fun imgSrc(iv: ImageView, src: Any?) {

    // 原来的写法
    // val imgRes = when(src) {
    //     is String -> {
    //         when {
    //             src.startsWith("//img.cniao5.com") -> "https:$src"
    //             src.startsWith("/img.cniao5.com") -> "https:/$src"
    //             else -> src
    //         }
    //     }
    // }

    // region 自定义
    val imgRes = when(src) {
        is String -> {
            when {
                src.startsWith("//img.cniao5.com") -> "https:$src"
                src.startsWith("/img.cniao5.com") -> "https:/$src"
                else -> null
            }
        } else -> src
    }
    var placeholder =  R.drawable.placeholder
    var imgRes2 = imgRes ?: R.drawable.placeholder // 这行代码是做传统的非空判断
    if (iv is ShapeableImageView) { // 圆形imageView设置圆形占位图
        placeholder = R.drawable.logo // 这行代码是做传统的非空判断
        imgRes2 = imgRes ?: R.drawable.logo // 当src为null圆形imageView设置圆形本地图片
    }
    // endregion 自定义

    // 原来的写法
    // Glide.with(iv).load(imgRes).into(iv)
    Glide.with(iv).load(imgRes2).placeholder(placeholder).into(iv)
}

@BindingAdapter("tint")
fun imgColor(iv: ImageView, color: Int) {
    if (color != 0) {
        runCatching {
            iv.setColorFilter(iv.resources.getColor(color))
        }.onFailure {
            iv.setColorFilter(color)
        }
    }
}

// app:tint -> tint
//@BindingAdapter("tint")
//fun imgColor(iv: ImageView, @ColorRes color: Int) {
//    if (color != 0) {
//        iv.setColorFilter(iv.resources.getColor(color))
//    }
//}

// app:tint -> tint
//@BindingAdapter("tint")
//fun imgColor2(iv: ImageView, @ColorInt color: Int) {
//    if (color != 0) {
//        iv.setColorFilter(color)
//    }
//}

@BindingAdapter("android:textColor")
fun tvColor(tv: TextView, color: Int) {
    if (color != 0) {
        runCatching {
            tv.setTextColor(tv.resources.getColor(color))
        }.onFailure {
            tv.setTextColor(color)
        }
    }
}

//@BindingAdapter("android:textColor")
//fun tvColor(tv: TextView, @ColorRes color: Int) {
//    if (color != 0) {
//        tv.setTextColor(tv.resources.getColor(color))
//    }
//}

//@BindingAdapter("android:textColor")
//fun tvColor2(tv: TextView, @ColorInt color: Int) {
//    if (color != 0) {
//        tv.setTextColor(color)
//    }
//}