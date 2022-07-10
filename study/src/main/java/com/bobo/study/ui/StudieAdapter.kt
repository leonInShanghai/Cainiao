package com.bobo.study.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobo.common.webview.WebViewActivity
import com.bobo.study.databinding.ItemCourseStudyBinding
import com.bobo.study.net.StudiedRsp

/**
 * Created by 公众号：IT波 on 2022/6/19 Copyright © Leon. All rights reserved.
 * Functions: rv 适配器
 */
class StudieAdapter : RecyclerView.Adapter<StudiedVH>() {

    // 数据源
    private val mList = mutableListOf<StudiedRsp.Data>()

    // 刷新适配器
    fun submit(list: List<StudiedRsp.Data>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudiedVH
        = StudiedVH.createVH(parent)

    override fun onBindViewHolder(holder: StudiedVH, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        // mList?:return 0
        return mList.size
    }
}

class StudiedVH(private val binding: ItemCourseStudyBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createVH(parent: ViewGroup) : StudiedVH {
            return StudiedVH(
                ItemCourseStudyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(info: StudiedRsp.Data) {
        binding.info = info
        binding.npbProgressItemStudy.progress = info.progress.toInt()

        // region 自己增加
        binding.cdParent.setOnClickListener {
            WebViewActivity.openUrl(it.context, "https://mp.weixin.qq.com/s?__biz=MzI3NTc0NzI0NA==&mid=2247484061&" +
                    "idx=1&sn=d6661c299d64c50eb459d93cbd626557&chksm=eb015a5edc76d3485d5a100ad0ee12799f85303edd61b9e1f7" +
                    "a5316e0d6d4281281385479dff&mpshare=1&scene=23&srcid=0710cNQ7H9SuyXeaT8DLQI78&sharer_sharetime=165" +
                    "7423202461&sharer_shareid=205ec37e2b18cb79d8cf794b79891858#rd")
        }
        // endregion 自己增加

        binding.executePendingBindings()
    }

}