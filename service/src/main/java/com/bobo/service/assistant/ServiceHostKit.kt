package com.bobo.service.assistant

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.bobo.common.utils.*
import com.bobo.service.R
import com.didichuxing.doraemonkit.kit.AbstractKit

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions: 用于配置不同的接口host，测试工具
 */
class ServiceHostKit: AbstractKit() {

    override val icon: Int
        get() = R.drawable.icon_server_host

    override val name: Int
        get() = R.string.str_server_host_dokit

    override fun onAppInit(context: Context?) {

    }

    private val hostMap = mapOf<String, String>(
        "开发环境" to HOST_DEV,
        // "QA测试" to HOST_QA,
        "不可用的" to HOST_QA,
        "线上正式host" to HOST_PRODUCT
    )

    private val hosts = hostMap.values.toTypedArray()
    private val names = hostMap.keys.toTypedArray()

    override fun onClick(context: Context?) {
        val pos = hostMap.values.indexOf(getBaseHost()) % hostMap.size
        // 避免空指针
        context?:return ToastUtils.showShort(" -- context null --")
        // 弹窗，用于显示选择不同的host配置
        AlertDialog.Builder(context)
            .setTitle("切换Host")
            .setSingleChoiceItems(
                names, pos
            ) { dialog, which ->
                setBaseHost(hosts[which])
                dialog.dismiss()
            }.show()
    }
}