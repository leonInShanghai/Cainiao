package com.bobo.login

import androidx.activity.viewModels
import com.bobo.common.base.BaseActivity
import com.bobo.login.databinding.ActivityLoginBinding

/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions: 登陆Activity
 */
class LoginActivity: BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels { defaultViewModelProviderFactory }


    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun initView() {
        mBinding.apply {
             vm = viewModel
        }
    }

    override fun initConfig() {

    }

    override fun initData() {

    }

}

