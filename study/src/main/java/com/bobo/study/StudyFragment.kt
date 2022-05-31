package com.bobo.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.bobo.common.base.BaseFragment
import com.bobo.study.databinding.FragmentStudyBinding

class StudyFragment : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_study

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view)
    }
}