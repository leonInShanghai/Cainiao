package com.bobo.cainiao

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bobo.cainiao.databinding.ActivityMainBinding
import com.bobo.common.base.BaseActivity
import com.bobo.course.CourseFragment
import com.bobo.home.HomeFragment
import com.bobo.study.StudyFragment
import com.bobo.common.widget.BnvVp2Mediator
import com.bobo.mine.ui.MineContainerFragment


/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions: 首页第一个Activity
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    private val fragments = mapOf<Int, ReFragment>(
        INDEX_HOEM to { HomeFragment() },
        INDEX_COURSE to { CourseFragment() },
        INDEX_STUDY to { StudyFragment() },
        INDEX_MINE to { MineContainerFragment() }, // MineFragment()
    )

    override fun initConfig() {
        super.initConfig()
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            vp2Main.adapter = MainViewPagerAdapter(this@MainActivity, fragments)
            BnvVp2Mediator(bnvMain, vp2Main, {bnv, vp2->
                // 设置viewpager2用户不能滑动
                vp2.isUserInputEnabled = false
            }).attach()
        }
    }

    companion object {
        const val INDEX_HOEM = 0 // 首页索引位置
        const val INDEX_COURSE =1 // 课程索引位置
        const val INDEX_STUDY = 2 // 学习索引为
        const val INDEX_MINE = 3 // 我的索引位置
    }
}

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions: vp2Main 的适配器
 */
class MainViewPagerAdapter(fragmentActivity: FragmentActivity, val fragments: Map<Int, ReFragment>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragments.size

    // override fun createFragment(position: Int): Fragment {
    //     return fragments.get(position) ?: error("请确保fragments数据和vp2Main的index匹配")
    // }
    //创建fragment invoke回调函数，让它实例化创建新的对象
    override fun createFragment(position: Int) = fragments[position]?.invoke() ?: error("请确保fragment数据源和viewPager2的index匹配设置")
}

// 类型别名定义 传入的是一个代码块，每次都是一个新的Fragment
typealias ReFragment = () -> Fragment