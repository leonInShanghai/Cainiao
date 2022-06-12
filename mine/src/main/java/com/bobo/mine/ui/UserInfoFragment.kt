package com.bobo.mine.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bobo.common.base.BaseFragment
import com.bobo.mine.R
import com.bobo.mine.databinding.FragmentUserInfoBinding

/**
 * Created by 公众号：IT波 on 2022/6/12 Copyright © Leon. All rights reserved.
 * Functions: 用户信息界面fragment
 */
class UserInfoFragment : BaseFragment() {

    private val args by navArgs<UserInfoFragmentArgs>()

    override fun getLayoutRes(): Int = R.layout.fragment_user_info

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding =
        FragmentUserInfoBinding.bind(view).apply {
            // toolbar返回
            toolbarUserInfo.setNavigationOnClickListener { findNavController().navigateUp() }
            toolbarUserInfo.navigationIcon?.setTint(Color.WHITE)
            // 保存返回按钮
            btnSaveUserInfo.setOnClickListener { findNavController().navigateUp() }
            // 给viewModel赋值
            info = args.info
        }
}