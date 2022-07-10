package com.bobo.course

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.annotation.NonNull
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.bobo.common.base.BaseFragment
import com.bobo.course.databinding.*
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
    // private var mBd: FragmentCourseBinding? = null
    private lateinit var mBinding: FragmentCourseBinding
    // endregion 自定义

    // 初始化为all,点击上方tabitem之后记得重新赋值
    private var code = "all"     // 方向从课程分类接口获取    默认 all;例如 android,python
    private var difficulty = -1     // 难度 (-1 全部) (1 初级) (2 中级) (3 高级) (4 架构) 默认 -1
    private var is_free = -1     // 价格 (-1, 全部) （0 付费） (1 免费) 默认 -1
    private var q = -1   // 排序  (-1 最新) (1 评价最高)  (2 学习最多) 默认 -1


    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
//        return FragmentCourseBinding.bind(view).apply {
//            vm = viewModel
//        }

        mBinding = FragmentCourseBinding.bind(view).apply {
            vm = viewModel
        }
        return mBinding
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
                mBinding.pbFragmentCourse.visibility = if (it) View.VISIBLE else View.GONE
            }
            // endregion 自定义进度条显示

            // 课程分类
            liveTypes.observerKt { types->
                // 当遇到数据重复的bug时根据需要先清除再添加
                if (mBinding.tlCategoryCourse.tabCount >= 6) {
                    // mBd?.tlCategoryCourse?.removeAllViews()
                    return@observerKt
                }
                val tab = mBinding.tlCategoryCourse.newTab().also { tab->
                    tab.text = "全部"
                }
                tab.let { mBinding.tlCategoryCourse.addTab(it) }
                types?.forEach { item->
                    val tab =  mBinding.tlCategoryCourse?.newTab().also { tab->
                        tab.text = item.title
                    }
                    tab.let { mBinding.tlCategoryCourse?.addTab(it) }
                }
            }

            mBinding.tlCategoryCourse?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
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

                    // 这句代码很上面的一样，可以使用
                    // refreshData()

                    LogUtils.i("tab 个数 ${ mBinding.tlCategoryCourse?.tabCount} types size " +
                            " ${types.size}")
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                   
                }

            })

            adapter.addLoadStateListener { loadState->
                when(loadState.refresh) {
                     is LoadState.NotLoading ->{
                         // mBinding.pbFragmentCourse.visibility = View.GONE
                     }
                     is LoadState.Loading -> {
                         // mBinding.pbFragmentCourse.visibility = View.VISIBLE
                     }
                     is LoadState.Error -> {
                         // mBinding.pbFragmentCourse.visibility = View.GONE
                         //获取加载错误事件
                         val error = when {
                             loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                             loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                             loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                             else -> null
                         }

                         error?.let {
                             ToastUtils.showShort(it.error.message)
                         }
                     }
                }
                LogUtils.i("LoadState $loadState")
            }

            // 全部类型按钮点击事件
            mBinding.spinnerTypeCourse.setOnClickListener {
                popupWindow_type.showAsDropDown(it)
            }

            // 全部难度按钮点击事件
            mBinding.spinnerLevelCourse.setOnClickListener {
                popupWindow_level.showAsDropDown(it)
            }

            // 全部价格按钮点击事件
            mBinding.spinnerPriceCourse.setOnClickListener {
                popupWindow_price.showAsDropDown(it)
            }

            // 默认排序按钮点击事件
            mBinding.spinnerSortCourse.setOnClickListener {
                popupWindow_sort.showAsDropDown(it)
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
        configPopFilter()
    }

    // popupWindow 在activity最上层显示一个弹窗
    private lateinit var popupWindow_type: PopupWindow //全部类型
    private lateinit var popupWindow_level: PopupWindow //全部难度
    private lateinit var popupWindow_price: PopupWindow //全部价格
    private lateinit var popupWindow_sort: PopupWindow //默认排序

    // popupwindow初始化和点击事件处理
    private fun configPopFilter() {

        //region 全部类型弹窗
        val popBinding = PopCourseTypeBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        popupWindow_type = PopupWindow(
            popBinding.root,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // 点击外部消失和穿透事件处理
        outSideTouch(popupWindow_type)

        //一个有初始值的observable，用来显示弹窗文字是否被点击和右边的勾显示与否，被点击set值，控件获取到对应的值后改变文字颜色
        val obCheckPos = ObservableInt(0)

        popBinding.apply {
            pos = obCheckPos
            tvAllType.setOnClickListener {
                obCheckPos.set(0)
                mBinding.spinnerTypeCourse.text = "全部类型"
                popupWindow_type.dismiss() //解除popup
                refreshData()
            }
            tvBizType.setOnClickListener {
                obCheckPos.set(1)
                mBinding.spinnerTypeCourse.text = "商业实战"
                popupWindow_type.dismiss()
                refreshData()
            }
            tvProType.setOnClickListener {
                obCheckPos.set(2)
                mBinding.spinnerTypeCourse.text = "专业好课"
                popupWindow_type.dismiss()
                refreshData()
            }
        }
        // endregion

        //region 全部难度弹窗
        val popBinding1 = PopCourseLevelBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        popupWindow_level = PopupWindow(
            popBinding1.root,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        //点击外部消失和穿透事件处理
        outSideTouch(popupWindow_level)

        //一个有初始值的observable，用来显示弹窗文字是否被点击和右边的勾显示与否，被点击set值，控件获取到对应的值后改变文字颜色
        val obCheckPos1 = ObservableInt(0)

        popBinding1.apply {
            pos = obCheckPos1
            tvAll.setOnClickListener {
                obCheckPos1.set(0)
                mBinding.spinnerLevelCourse.text = "全部难度"
                difficulty = -1
                popupWindow_level.dismiss() //解除popup
                refreshData()
            }
            tvTwo.setOnClickListener {
                obCheckPos1.set(1)
                mBinding.spinnerLevelCourse.text = "初级"
                difficulty = 1
                popupWindow_level.dismiss() //解除popup
                refreshData()
            }
            tvThree.setOnClickListener {
                obCheckPos1.set(2)
                mBinding.spinnerLevelCourse.text = "中级"
                difficulty =2
                popupWindow_level.dismiss() //解除popup
                refreshData()
            }
            tvFour.setOnClickListener {
                obCheckPos1.set(3)
                mBinding.spinnerLevelCourse.text = "高级"
                difficulty = 3
                popupWindow_level.dismiss() //解除popup
                refreshData()
            }
            tvFive.setOnClickListener {
                obCheckPos1.set(4)
                mBinding.spinnerLevelCourse.text = "架构"
                difficulty = 4
                popupWindow_level.dismiss() //解除popup
                refreshData()
            }
        }
        //endregion

        //region 全部价格弹窗
        val popBinding2 = PopCoursePriceBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        popupWindow_price = PopupWindow(
            popBinding2.root,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        //点击外部消失和穿透事件处理
        outSideTouch(popupWindow_price)

        //一个有初始值的observable，用来显示弹窗文字是否被点击和右边的勾显示与否，被点击set值，控件获取到对应的值后改变文字颜色
        val obCheckPos2 = ObservableInt(0)

        popBinding2.apply {
            pos = obCheckPos2
            tvAll.setOnClickListener {
                obCheckPos2.set(0)
                mBinding.spinnerPriceCourse.text = "全部价格"
                is_free = -1
                popupWindow_price.dismiss() //解除popup
                refreshData()
            }
            tvTwo.setOnClickListener {
                obCheckPos2.set(1)
                mBinding.spinnerPriceCourse.text = "免费"
                is_free = 1
                popupWindow_price.dismiss() //解除popup
                refreshData()
            }
            tvThree.setOnClickListener {
                obCheckPos2.set(2)
                mBinding.spinnerPriceCourse.text = "付费"
                is_free = 0
                popupWindow_price.dismiss() //解除popup
                refreshData()
            }
        }
        //endregion

        //region 默认排序弹窗
        val popBinding3 = PopCourseSortBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        popupWindow_sort = PopupWindow(
            popBinding3.root,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        //点击外部消失和穿透事件处理
        outSideTouch(popupWindow_sort)

        //一个有初始值的observable，用来显示弹窗文字是否被点击和右边的勾显示与否，被点击set值，控件获取到对应的值后改变文字颜色
        val obCheckPos3 = ObservableInt(0)

        popBinding3.apply {
            pos = obCheckPos3
            tvAll.setOnClickListener {
                obCheckPos3.set(0)
                mBinding.spinnerSortCourse.text = "默认排序"
                q = -1
                popupWindow_sort.dismiss() //解除popup
                refreshData()
            }
            tvTwo.setOnClickListener {
                obCheckPos3.set(1)
                mBinding.spinnerSortCourse.text = "评价最高"
                q = 0
                popupWindow_sort.dismiss() //解除popup
                refreshData()
            }
            tvThree.setOnClickListener {
                obCheckPos3.set(2)
                mBinding.spinnerSortCourse.text = "学习最多"
                q = 1
                popupWindow_sort.dismiss() //解除popup
                refreshData()
            }
        }
        //endregion

    }

    // 请求数据
    private fun refreshData(courseType: Int = -1) {
        // viewModel.getCourseList(code, difficulty, is_free,q)
        lifecycleScope.launchWhenCreated {
            viewModel.typedCourseList(course_type = -1, code = code, difficulty, is_free, q).collectLatest {
                //  coursePagingAdapter.submitData(it)
                viewModel.adapter.submitData(it)
            }
        }
    }

    /**
     * PopupWindow outsidetouchable和点击穿透事件处理
     */
    private fun outSideTouch(popupwindow: PopupWindow) {
        //弹窗背景（大部分被控件遮挡）
        popupwindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupwindow.isOutsideTouchable = true //点击外部消失
        popupwindow.isFocusable = true  //focusable容易忽略 防止点击事件穿透
    }

}