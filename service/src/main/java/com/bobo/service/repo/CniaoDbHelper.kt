package com.bobo.service.repo

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by 公众号：IT波 on 2022/6/11 Copyright © Leon. All rights reserved.
 * Functions: 数据库操作帮助类
 */
object CniaoDbHelper {

    /**
     * 查询room数据库中 用户数据 LiveData<CniaoUserInfo>
     */
    fun getLiveUserInfo(context: Context): LiveData<CniaoUserInfo> =
        CniaoDatabase.getInstance(context).userDao.queryLiveUser() // 可以不传参数不传参数则为0

    /**
     * 查询room数据库中用户数据 CniaoUserInfo
     */
    fun getUserInfo(context: Context): CniaoUserInfo? =
        CniaoDatabase.getInstance(context).userDao.queryUser() // 可以不传参数不传参数则为0

    /**
     * 删除数据库中userinfo的信息
     */
    fun deleteUserInfo(context: Context) {

        // val currentUser = getUserInfo(context) ?: return
        // CniaoDatabase.getInstance(context).userDao.deleteUser(currentUser)

        GlobalScope.launch(Dispatchers.IO) {
            getUserInfo(context) ?.let { info ->
                CniaoDatabase.getInstance(context).userDao.deleteUser(info)
            }
        }
    }

    /**
     * 新增用户到数据库
     */
    fun insertUserInfo(context: Context, user: CniaoUserInfo) {
        // GlobalScope.launch创建主协程
        GlobalScope.launch(Dispatchers.IO) {
            CniaoDatabase.getInstance(context).userDao.insterUser(user)
        }
    }
}