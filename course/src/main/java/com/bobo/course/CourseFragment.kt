package com.bobo.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bobo.common.base.BaseFragment
import com.bobo.course.databinding.FragmentCourseBinding

/**
 * Created by 公众号：IT波 on 2022/5/30 Copyright © Leon. All rights reserved.
 * Functions: 课程碎片
 */
class CourseFragment: BaseFragment(R.layout.fragment_course) {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_course
    }

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentCourseBinding.bind(view)
    }

}