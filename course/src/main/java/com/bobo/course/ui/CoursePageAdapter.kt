package com.bobo.course.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bobo.course.databinding.ItemCourseRecycBinding
import com.bobo.course.net.CourseListRsp
import com.bobo.course.ui.playvideo.PlayVideoActivity

/**
 * Created by 公众号：IT波 on 2022/6/25 Copyright © Leon. All rights reserved.
 * Functions:
 */
class CoursePageAdapter : PagingDataAdapter<CourseListRsp.Data, CourseVH>(differCallback) {

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  CourseVH.createVH(parent)

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<CourseListRsp.Data>() {
            override fun areItemsTheSame(
                oldItem: CourseListRsp.Data,
                newItem: CourseListRsp.Data)
                    : Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CourseListRsp.Data,
                newItem: CourseListRsp.Data)
                    : Boolean {
                return oldItem == newItem
            }
        }
    }
}

class CourseVH(private val binding: ItemCourseRecycBinding) : RecyclerView.ViewHolder(binding.root) {

    // [kəmˈpæniən]
    companion object {
        fun createVH(parent: ViewGroup) : CourseVH {
            return CourseVH(
                ItemCourseRecycBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
        }
    }

    fun bind(info: CourseListRsp.Data) {
        binding.info = info
        // 删除线的实现
        binding.tvOldPriceItemCourse.paint.flags += Paint.STRIKE_THRU_TEXT_FLAG

        // region 自己增加，点击item跳转到 PlayVideoActivity
        binding.cdParentCourse.setOnClickListener {
            // requireContext()
            PlayVideoActivity.openPlayVideo(it.context, "http://mpvideo.qpic.cn/0bf2yiaaqaaapaam2nxsfnpvbqwdbdbaac" +
                    "aa.f10002.mp4?dis_k=f97a5c4392fb8d6c10b7242f7223ac4e&dis_t=1657440643&vid=wxv_1680298232922341383" +
                    "&format_id=10002&support_redirect=0&mmversion=false", info)
        }
        // endregion 自己增加，点击item跳转到 PlayVideoActivity

        // 执行挂起的绑定
        binding.executePendingBindings()
    }
}