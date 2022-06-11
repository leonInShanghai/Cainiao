package com.bobo.mine

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.bobo.common.base.BaseFragment
import com.bobo.mine.databinding.FragmentMineBinding
import com.bobo.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by 公众号：IT波 on 2022/6/11 Copyright © Leon. All rights reserved.
 * Functions: 我的界面
 * 测试账号 18648957777
 * 测试账号 cn5123456
 */
class MineFragment : BaseFragment() {

    private val viewModel: MineViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view).apply {
            vm = viewModel

            // UI操作 退出按钮点击事件处理
            btnLogoutMine.setOnClickListener {
                CniaoDbHelper.deleteUserInfo(requireContext())
                // 这里使用ARouter框架实现页面的跳转并非传统的startActivity
                ARouter.getInstance().build("/login/login").navigation()
            }
        }
    }

    override fun initData() {
        super.initData()
        CniaoDbHelper.getLiveUserInfo(requireContext()).observerKt {
            viewModel.liveUser.value = it
        }
    }
}