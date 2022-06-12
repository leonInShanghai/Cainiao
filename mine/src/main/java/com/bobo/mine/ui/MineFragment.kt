package com.bobo.mine.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.bobo.common.base.BaseFragment
import com.bobo.mine.MineViewModel
import com.bobo.mine.R
import com.bobo.mine.databinding.FragmentMineBinding
import com.bobo.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by 公众号：IT波 on 2022/6/11 Copyright © Leon. All rights reserved.
 * Functions: 我的界面
 * 测试账号 18648957777
 * 测试账号 cn5123456
 * debug第三方控件 https://www.dokit.cn/
 */
class MineFragment : BaseFragment() {

    private val viewModel: MineViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view).apply {
            vm = viewModel

            // UI操作 退出按钮点击事件处理
            btnLogoutMine.setOnClickListener {
                // 删除数据库中登录信息
                CniaoDbHelper.deleteUserInfo(requireContext())
                // 这里使用ARouter框架实现页面的跳转并非传统的startActivity
                ARouter.getInstance().build("/login/login").navigation()
            }

            // 点击头像跳转到UserInfoFragment
            ivUserIconMine.setOnClickListener {
                val info = viewModel.liveinfo.value

                // region 自己增加未登录提示用户去登录
                info?:Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show()
                // endregion 自己增加未登录提示用户去登录

                info?.let {
                    // 由于服务器返回的数据中有info.company这个字段所有就注释了
                    // info.company = "谷歌公司老板"
                    val action = MineFragmentDirections.actionMineFragmentToUserInfoFragment(info)
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun initData() {
        super.initData()
        CniaoDbHelper.getLiveUserInfo(requireContext()).observerKt {
            // viewModel.liveUser.value = it
            it?.let {
                viewModel.getUserInfo(it?.token)
            }
        }
    }
}