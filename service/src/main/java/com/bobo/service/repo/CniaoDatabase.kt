package com.bobo.service.repo

import android.content.Context
import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by 公众号：IT波 on 2022/6/11 Copyright © Leon. All rights reserved.
 * Functions: 菜鸟app的公共业务基础数据类, 用户登录后的响应对象及数据库操作
 */

// 3.Database
@Database(entities = [CniaoUserInfo::class], version = 1, exportSchema = false)
abstract class CniaoDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object{
        private const val CNIAO_DA_NEME = "cniao_db"

        @Volatile // 声明多线程态共享的一个数据对象
        private var instance: CniaoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CniaoDatabase {
            return instance?:Room.databaseBuilder(
                context,
                CniaoDatabase::class.java,
                CNIAO_DA_NEME)
                .build().also { instance = it }
        }
    }
}

// 1.entity的定义
// 手机号和密码登陆 接口响应, 请求回来的数据需要保存在本地数据库
@Entity(tableName = "tb_cniao_user")
data class CniaoUserInfo(
    @PrimaryKey
    val id: Int, // 数据库主键id
    val course_permission:Boolean,
    val token: String?,
    @Embedded
    val user: User?
) {
    @Keep
    data class User(
        @ColumnInfo(name = "cniao_user_id") // 当两个字段重复时使用ColumnInfo起一个别名避免重复
        val id: Int,  // 用户id
        val logo_url:String?, // 用户头像url
        val reg_time:String?, // 用户注册时间
        val username:String?  // 用户名
    )

}

// 2.dao层的定义
@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insterUser(user: CniaoUserInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: CniaoUserInfo)

    @Delete
    fun deleteUser(user: CniaoUserInfo)

    // 这种写法也能用不用直接查询id为0的用户，下面的方法更灵活
//    @Query("select * from tb_cniao_user where id=0")
//    fun queryLiveUser(): LiveData<CniaoUserInfo>

    @Query("select * from tb_cniao_user where id=:id")
    fun queryLiveUser(id: Int=0): LiveData<CniaoUserInfo>

    @Query("select * from tb_cniao_user where id=:id")
    fun queryUser(id: Int=0): CniaoUserInfo?
}
