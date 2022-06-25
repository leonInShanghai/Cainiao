package com.bobo.study.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        binding.executePendingBindings()
    }

}