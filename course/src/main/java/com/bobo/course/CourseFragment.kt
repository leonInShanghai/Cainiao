package com.bobo.course

import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.blankj.utilcode.util.LogUtils
import com.bobo.common.base.BaseFragment
import com.bobo.course.databinding.FragmentCourseBinding
import com.bobo.course.ui.CoursePageAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by 公众号：IT波 on 2022/5/30 Copyright © Leon. All rights reserved.
 * Functions: 课程碎片 R.layout.fragment_course
 */
class CourseFragment: BaseFragment() {

    private val viewModel: CourseViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_course

    // region 自定义
    private var mBd: FragmentCourseBinding? = null
    // endregion 自定义

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
//        return FragmentCourseBinding.bind(view).apply {
//            vm = viewModel
//        }

        mBd = FragmentCourseBinding.bind(view).apply {
            vm = viewModel
        }
        return mBd as @NonNull FragmentCourseBinding
    }

    // private val adapter = CoursePageAdapter()
    
    override fun initData() {
        super.initData()
        // 获取课程类型下的数据
        viewModel.getCourseTypeList()
        viewModel.apply {

            // region 自定义进度条显示
            isLoading.observe(viewLifecycleOwner) {
                //协程block获取数据代码块是否结束，协程结束时为false
                mBd?.pbFragmentCourse?.visibility = if (it) View.VISIBLE else View.GONE
            }
            // endregion 自定义进度条显示

            // 课程分类
            liveTypes.observerKt { types->
                // 当遇到数据重复的bug时根据需要先清除再添加
                if (mBd?.tlCategoryCourse?.tabCount!! >= 6) {
                    // mBd?.tlCategoryCourse?.removeAllViews()
                    return@observerKt
                }
                val tab = mBd?.tlCategoryCourse?.newTab().also { tab->
                    tab?.text = "全部"
                }
                tab?.let { mBd?.tlCategoryCourse?.addTab(it) }
                types?.forEach { item->
                    val tab =  mBd?.tlCategoryCourse?.newTab().also { tab->
                        tab?.text = item.title
                    }
                    tab?.let { mBd?.tlCategoryCourse?.addTab(it) }
                }
            }

            mBd?.tlCategoryCourse?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val types = liveTypes.value ?: return
                    // mBd?.tlCategoryCourse?.tabCount
                    // 第0个全部
                    val index: Int = tab?.position ?: 0
                    val selCode: String = if (index > 0) {
                        types.get(index - 1).code ?: "all"
                    } else "all"

                    lifecycleScope.launchWhenCreated {
                        typedCourseList(code = selCode).collectLatest {
                            adapter.submitData(it)
                        }
                    }

                    LogUtils.i("tab 个数 ${ mBd?.tlCategoryCourse?.tabCount} types size " +
                            " ${types.size}")
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                   
                }

            })

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

//            lifecycleScope.launchWhenCreated {
//                typedCourseList().observerKt { data ->
//                    data?.let {
//                        adapter.submitData(lifecycle, data)
//                    }
//                }
//                // 第二种写法
//                // typedCourseList().asFlow().collectLatest { data ->
//                //     adapter.submitData(lifecycle, data)
//                // }
//            }
        }

        // 筛选点击
        popFilter()
    }

    private lateinit var popwindow: PopupWindow

    private fun popFilter() {

    }

}