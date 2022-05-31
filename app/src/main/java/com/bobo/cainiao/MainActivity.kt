package com.bobo.cainiao

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bobo.cainiao.databinding.ActivityMainBinding
import com.bobo.common.base.BaseActivity
import com.bobo.common.ktx.bindView

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions: 首页第一个Activity
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initConfig() {
        super.initConfig()
    }

    override fun initView() {
        super.initView()
        val navController = findNavController(R.id.fcv_main)
        mBinding.bnvMain.setupWithNavController(navController)
    }

    override fun initData() {
        super.initData()
    }
}