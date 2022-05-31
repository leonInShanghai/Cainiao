package com.bobo.mine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.bobo.common.base.BaseFragment
import com.bobo.mine.databinding.FragmentMineBinding

class MineFragment : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view)
    }
}