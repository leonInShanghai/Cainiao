package com.bobo.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.bobo.common.base.BaseFragment
import com.bobo.home.databinding.FragmentHomeBinding

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions:
 */
class HomeFragment: BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentHomeBinding.bind(view)
    }
}