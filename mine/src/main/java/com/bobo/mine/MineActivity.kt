package com.bobo.mine

import android.graphics.Color
import android.widget.Toast
import androidx.databinding.ObservableField
import com.bobo.common.base.BaseActivity
import com.bobo.common.ktx.context
import com.bobo.mine.databinding.ActivityMineBinding
import com.bobo.mine.widget.ItemSettingsBean

/**
 * Created by 公众号：IT波 on 2022/6/4 Copyright © Leon. All rights reserved.
 * Functions: 我的模块
 */
class MineActivity : BaseActivity<ActivityMineBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_mine
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            val ib: ItemSettingsBean = ItemSettingsBean(iconRes = R.drawable.logo,title = "学习卡")
            val obBean = ObservableField(ib)
            // isvCard.setInfo(bean)
            bean = obBean
            ib.title = "你的学习卡"
            ib.titleColor = R.color.colorPrimaryDark
            ib.arrowColor = Color.RED
            ib.iconRes = "https://profile.csdnimg.cn/8/1/4/0_weixin_39709134"

            isvCard.onClickArrow {
                Toast.makeText(context, "点击了Arrow", Toast.LENGTH_SHORT).show()
            }

            isvCard.setOnClickListener {
                Toast.makeText(context, "点击了整个item", Toast.LENGTH_SHORT).show()
            }
        }
    }
}