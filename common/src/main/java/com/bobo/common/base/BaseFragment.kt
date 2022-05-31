package com.bobo.common.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by 公众号：IT波 on 2022/5/30 Copyright © Leon. All rights reserved.
 * Functions:Fragment的抽象类
 */
abstract class BaseFragment: Fragment {

    constructor(): super()

    constructor(@LayoutRes layout: Int) : super(layout)

    private var mBinding: ViewDataBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = bindView(view, savedInstanceState)
        mBinding?.lifecycleOwner = viewLifecycleOwner
        initConfig()
        initData()
        Log.d("Leon", "onViewCreated: " + this.javaClass.simpleName)
    }

    // 强制子类实现获取布局id
    @LayoutRes
    abstract fun getLayoutRes(): Int

    // 强制子类实现并返回一个ViewDataBinding
    abstract fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding

    open fun initConfig() {

    }

    open fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }

    /**
     * 扩展liveData的observe函数
     */
    protected fun <T: Any> LiveData<T>.observerKt(block:(T?)->Unit) {
        this.observe(viewLifecycleOwner, Observer {data->
            // block.invoke(data) ← 也可以这样写
            block(data)
        })
    }
}