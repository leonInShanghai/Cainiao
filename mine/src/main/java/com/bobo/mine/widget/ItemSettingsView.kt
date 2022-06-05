package com.bobo.mine.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableField
import com.bobo.mine.R
import com.bobo.mine.databinding.VItemSettingsBinding

/**
 * Created by 公众号：IT波 on 2022/6/4 Copyright © Leon. All rights reserved.
 * Functions:自定义设置item的组合控件
 */
class ItemSettingsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var itemBean = ItemSettingsBean()
    private val obItemInto = ObservableField(itemBean)

    init {
        // attachToRoot(第三个参数)此时（自定义（view）组合控件）应该写true
        // 管理布局layout
        VItemSettingsBinding.inflate(LayoutInflater.from(context), this, true).apply {
            // 给viewModel赋值
            info = obItemInto
        }
        setBackgroundColor(Color.WHITE)

        // 读取配置属性
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemSettingsView).apply {
            itemBean.iconRes = getResourceId(R.styleable.ItemSettingsView_icon, R.drawable.logo)
            val iconRGB = getColor(R.styleable.ItemSettingsView_iconColor, 0)
            itemBean.iconColor = iconRGB
            // title设置  Title标题
            itemBean.title = getString(R.styleable.ItemSettingsView_title) ?: ""
            val titleRGB = getColor(R.styleable.ItemSettingsView_titleColor, resources.getColor(R.color
                .colorPrimaryText))
            itemBean.titleColor = titleRGB
            // desc设置 标题内容描述
            itemBean.desc = getString(R.styleable.ItemSettingsView_desc) ?: ""
            val descRGB = getColor(R.styleable.ItemSettingsView_descColor, 0)
            itemBean.descColor = descRGB
            // arrow设置
            itemBean.arrowRes = getResourceId(R.styleable.ItemSettingsView_arrow, R.drawable.ic_right)
            val  arrowRGB = getColor(
                R.styleable.ItemSettingsView_arrowColor,
                resources.getColor(R.color.colorSecondaryText)
            )
            itemBean.arrowColor = arrowRGB
        }

        // region 回收资源
        attributes.recycle()
        // endregion 回收资源
    }

    // region 设置资源

    /**
     * 设置整个item对象info
     */
    fun setInfo(info: ItemSettingsBean) {
        itemBean = info
        obItemInto.set(info)
    }

    /**
     * 设置title
     */
    fun setTitle(title: String) {
        itemBean.title = title
    }

    /**
     * 设置内容描述
     */
    fun setDesc(desc: String) {
        itemBean.desc = desc
    }

    /**
     * 设置icon图标
     */
    fun setIcon(iconRes: Any) {
        itemBean.iconRes = iconRes
    }

    /**
     * 设置箭头图标
     */
    fun setAttow(arrowRes: Any) {
        itemBean.arrowRes = arrowRes
    }

    // endregion 设置资源

    // region 点击事件

    /**
     * 单独点击图标
     */
    fun onClickIcon(listener: OnClickListener) {
        itemBean.iconListener = listener
    }

    /**
     * 单独点击title
     */
    fun onClickTitle(listener: OnClickListener) {
        itemBean.titleListener = listener
    }

    /**
     * 单独点击desc
     */
    fun onClickDesc(listener: OnClickListener) {
        itemBean.descListener = listener
    }

    /**
     * 单独点击箭头
     */
    fun onClickArrow(listener: OnClickListener) {
        itemBean.arrowListener = listener
    }

    // endregion 点击事件

    // region 设置颜色

    /**
     * 设置Icon颜色
     */
    fun setIconColor(colorRes: Int) {
         itemBean.iconColor = colorRes
    }

    /**
     * 设置标题颜色
     */
    fun setTitleColor(colorRes: Int) {
        itemBean.titleColor = colorRes
    }

    /**
     * 设置描述颜色
     */
    fun setDescColor(colorRes: Int) {
        itemBean.descColor = colorRes
    }

    /**
     * 设置箭头arrow颜色
     */
    fun setArrowColor(colorRes: Int) {
        itemBean.arrowColor = colorRes
    }

    // endregion 设置颜色

    // 重写view的点击事件拦截，根据需要当自己监听点击事件不要子类监听的时候直接返回true
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // 如果自己设置了点击事件自己监听不要子view监听，达到整个item点击功能
        return hasOnClickListeners()
    }
}

data class ItemSettingsBean(
    var iconRes: Any = R.drawable.logo,
    var title: String = "Title标题",
    var desc: String = "标题内容描述",
    var titleColor: Int = R.color.colorPrimaryText,
    var descColor: Int = R.color.colorSecondaryText,
    var iconColor: Int = 0,
    var arrowColor: Int = 0,
    var arrowRes: Any = R.drawable.ic_right // 右边箭头图片
    ) {
    // itemview的子view点击事件
    var iconListener: View.OnClickListener? = null
    var titleListener: View.OnClickListener? = null
    var descListener: View.OnClickListener? = null
    var arrowListener: View.OnClickListener? = null
}