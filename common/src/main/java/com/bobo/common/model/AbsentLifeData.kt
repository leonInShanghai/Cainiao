package com.bobo.common.model

import androidx.lifecycle.LiveData

/**
 * Created by 公众号：IT波 on 2022/5/30 Copyright © Leon. All rights reserved.
 * Functions: 创建一个空的LiveData对象类
 */
class AbsentLifeData<T: Any> private constructor(): LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T: Any> create(): LiveData<T> {
            return AbsentLifeData()
        }
    }
}