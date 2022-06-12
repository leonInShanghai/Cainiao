package com.bobo.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.bobo.common.base.BaseFragment
import com.bobo.mine.R
import com.bobo.mine.databinding.FragmentContainerMineBinding

/**
 * Created by 公众号：IT波 on 2022/6/12 Copyright © Leon. All rights reserved.
 * Functions: 我的模块的主Fragment，用于内部navigation的容器
 */
class MineContainerFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.fragment_container_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding
        =  FragmentContainerMineBinding.bind(view)

}