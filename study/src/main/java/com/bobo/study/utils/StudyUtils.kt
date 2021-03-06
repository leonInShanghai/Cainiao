package com.bobo.study.utils

import androidx.databinding.BindingAdapter
import com.bobo.study.net.StudiedRsp
import com.daimajia.numberprogressbar.NumberProgressBar

/**
 * Created by 公众号：IT波 on 2022/6/19 Copyright © Leon. All rights reserved.
 * Functions:
 */
object StudyUtils {

    @JvmStatic
    fun rankStr(rank: Int) : String {
        return if (rank > 0) "第${rank}名" else "千里之外"
    }

    @JvmStatic
    fun parseStudentComment(info: StudiedRsp.Data?): String {
        return "${info?.lessonsPlayedTime} ${info?.commentCount}人评价"
    }

    @JvmStatic
    fun parseFree(info: StudiedRsp.Data?): String {
        return if (info?.isFree == 1) "免费" else "￥${info?.nowPrice}"
    }

    /*
    * 有两种格式的返回值，判断该用哪种
    * */
    @JvmStatic
    fun parseTitle(info: StudiedRsp.Data?): String {
        return if (info?.course != null) {
            "${info.course.name}"
        } else {
            "${info?.name}"
        }
    }

    /*
    * 有两种格式的返回值，判断该用哪种
    * */
    @JvmStatic
    fun parseImage(info: StudiedRsp.Data?): String {
        return if (info?.course != null) {
            "${info.course.imgUrl}"
        } else if (info?.imgUrl != null){
            "${info?.imgUrl}"
        } else {
             ""
        }
    }

    /**
     *  返回0-100之间随机的一个Double类型整数
     */
    @JvmStatic
    fun getRandomProgress(): Double {
         return (0..100).random().toDouble()/100
    }

}

/**
 * NumberProgressBar扩展函数 将传来的progress*100转化为百分比形式 并且支持databinding加载
 */
@BindingAdapter("app:progress_current", requireAll = false)
fun setProgress(pb: NumberProgressBar, progress: Double?) {
    pb.progress = ((progress ?: 0.0) * 100).toInt() //*100，转化为百分比显示
}
