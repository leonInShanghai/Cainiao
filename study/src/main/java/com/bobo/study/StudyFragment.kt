package com.bobo.study

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.bobo.common.base.BaseFragment
import com.bobo.service.repo.CniaoDbHelper
import com.bobo.study.databinding.FragmentStudyBinding
import com.bobo.study.ui.StudieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StudyFragment : BaseFragment() {

    private val viewModel: StudyViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_study

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view).apply {
            // viewModel赋值
            vm = viewModel
            // region 自定义
            rvStudy.adapter = myAdapter
            // endregion 自定义
        }
    }

    // region 自定义
    private val myAdapter: StudieAdapter = StudieAdapter()
    // endregion 自定义

    override fun initData() {
        super.initData()

        // 观察数据库中的userInfo
        CniaoDbHelper.getLiveUserInfo(requireContext())
            .observerKt {
                viewModel.obUserInfo.set(it)
            }
        viewModel.getStudyData()
        // 获取到最近学习的数据列表
        viewModel.apply {
            liveStudyList.observerKt {
                // region 自定义
                myAdapter.submit(it?.datas ?: emptyList())
                // endregion 自定义
            }
            // lifecycleScope.launchWhenStarted {
            //     pagingData().observerKt { data ->
            //         data?.let {
            //             adapter.submitData(lifecycle, data)
            //         }
            //     }
            // }
        }
    }
}