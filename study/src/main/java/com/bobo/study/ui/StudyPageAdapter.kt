package com.bobo.study.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bobo.study.net.StudiedRsp

/**
 * Created by 公众号：IT波 on 2022/6/19 Copyright © Leon. All rights reserved.
 * Functions: recyclerView 加载分页的适配器
 */
class StudyPageAdapter : PagingDataAdapter<StudiedRsp.Data, StudiedVH>(differCallback) {

    override fun onBindViewHolder(holder: StudiedVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudiedVH
        = StudiedVH.createVH(parent)

    companion object {

        private val differCallback = object : DiffUtil.ItemCallback<StudiedRsp.Data>() {

            override fun areItemsTheSame(
                oldItem: StudiedRsp.Data,
                newItem: StudiedRsp.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StudiedRsp.Data,
                newItem: StudiedRsp.Data
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}