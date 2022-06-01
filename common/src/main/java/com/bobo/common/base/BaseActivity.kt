package com.bobo.common.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bobo.common.ktx.bindView
import com.bobo.common.ktx.viewLifeCycleOwner

/**
 * Created by 公众号：IT波 on 2022/5/30 Copyright © Leon. All rights reserved.
 * Functions: 简单封装的基类Activity
 */
abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity {

    constructor(): super()

    constructor(@LayoutRes layout: Int) : super(layout)

    protected lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = bindView<T>(getLayoutRes())
        initView()
        initConfig()
        initData()
    }

    // 强制子类实现获取布局文件id
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * 必要的View初始化
     */
    open fun initView() {

    }

    /**
     * 必要的配置初始化
     */
    open fun initConfig() {

    }

    /**
     * 必要的数据初始化
     */
    open fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        // 判断是否已初始化
        if (this::mBinding.isInitialized) {
            mBinding.unbind()
        }
    }

    /**
     * 扩展liveData的observe函数
     */
    protected inline fun <T: Any> LiveData<T?>.observerKt(crossinline block:(T?)->Unit) {
        this.observe(viewLifeCycleOwner, Observer {data->
            // block.invoke(data) ← 也可以这样写
            block(data)
        })
    }

}