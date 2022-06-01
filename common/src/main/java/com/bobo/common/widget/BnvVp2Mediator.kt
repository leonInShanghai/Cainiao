package com.bobo.common.widget

import android.view.MenuItem
import android.view.View
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by 公众号：IT波 on 2022/5/31 Copyright © Leon. All rights reserved.
 * Functions:
 */
class BnvVp2Mediator(private val bnv: BottomNavigationView, private val vp2: ViewPager2,
                     private val config: ((BottomNavigationView, ViewPager2)-> Unit)?=null) {

    private val map = mutableMapOf<MenuItem, Int>()

    init {
        bnv.menu.forEachIndexed { index, item ->
            // map.put(item, index) 和下面的这句一样
            map[item] = index
        }
    }

    fun attach() {
        config?.invoke(bnv, vp2)
        vp2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bnv.selectedItemId = bnv.menu.getItem(position).itemId
                // bnvMain.selectedItemId = when (position) {
                //     INDEX_HOEM -> R.id.homeFragment
                //     INDEX_COURSE -> R.id.courseFragment
                //     INDEX_STUDY -> R.id.studyFragment
                //     INDEX_MINE -> R.id.mineFragment
                //     else -> error("viewpager2的fragments索引${position}越界")
                // }

                // ViewPager2设置“overScrollMode”属性无效果
                val childat: View = vp2.getChildAt(0)
                if (childat is RecyclerView) {
                    childat.setOverScrollMode(View.OVER_SCROLL_NEVER)
                }

            }
        })

        bnv.setOnNavigationItemSelectedListener { item ->
            map.get(item)
            vp2.currentItem = map[item] ?: error("bnv的Id${item.itemId}没有对应上viewpager2的元素")

            // when (item.itemId) {
            //     R.id.homeFragment -> vp2Main.currentItem = INDEX_HOEM
            //     R.id.courseFragment -> vp2Main.currentItem = INDEX_COURSE
            //     R.id.studyFragment -> vp2Main.currentItem = INDEX_STUDY
            //     R.id.mineFragment -> vp2Main.currentItem = INDEX_MINE
            //     else -> error("bnv的Id${item.itemId}没有对应上viewpager2的元素")
            // }

            true
        }
    }
}