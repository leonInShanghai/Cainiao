package com.bobo.course

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.blankj.utilcode.util.LogUtils
import com.bobo.common.base.BaseFragment
import com.bobo.course.databinding.FragmentCourseBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by 公众号：IT波 on 2022/5/30 Copyright © Leon. All rights reserved.
 * Functions: 课程碎片 R.layout.fragment_course
 */
class CourseFragment: BaseFragment() {

    private val viewModel: CourseViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_course

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentCourseBinding.bind(view).apply {
            vm = viewModel
        }
    }

    override fun initData() {
        super.initData()
        // 获取课程类型下的数据
        viewModel.getCourseTypeList()
        viewModel.apply {
            adapter.addLoadStateListener { state->
                when(state.refresh) {
                     is LoadState.NotLoading ->{

                     }
                     is LoadState.Loading -> {

                     }
                     is LoadState.Error -> {

                     }
                }
                LogUtils.i("LoadState $state")
            }

            lifecycleScope.launchWhenCreated {
                typedCourseList().observerKt { data ->
                    data?.let {
                        adapter.submitData(lifecycle, data)
                    }
                }
                // 第二种写法
                // typedCourseList().asFlow().collectLatest { data ->
                //     adapter.submitData(lifecycle, data)
                // }
            }
        }

    }

}