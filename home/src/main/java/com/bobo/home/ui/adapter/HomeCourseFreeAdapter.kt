package com.bobo.home.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobo.home.net.LimitFreeList
import com.bobo.common.webview.WebViewActivity
import com.bobo.home.databinding.ItemHomeCourseBinding
import com.bobo.home.databinding.ItemHomeCourseFreeBinding
import com.bobo.home.net.HomeCourseItem
import com.bobo.home.net.NewClassList

/**
 * 新上好课/实战推荐适配器
 */
class HomeCourseFreeAdapter(private val mList: LimitFreeList) : RecyclerView.Adapter<HomeCourseFreeAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH.create(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size


    class VH(private val binding: ItemHomeCourseFreeBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): VH {
                val itemBinding =
                    ItemHomeCourseFreeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VH(itemBinding)
            }
        }

        fun bind(info: HomeCourseItem) {
            binding.info = info
            binding.tvOldPriceItemCourse.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG //中划线
            itemView.setOnClickListener { //itemView点击事件
                // ToastUtils.showShort("点击事件")
                WebViewActivity.openUrl(it.context, "https://www.cniao5.com/course/10201")
            }
            binding.executePendingBindings()
        }
    }
}