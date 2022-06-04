package com.bobo.service.network

import android.util.Log
import androidx.annotation.Keep
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.bobo.common.model.DataResult
import com.bobo.common.model.succeeded
import com.bobo.common.network.support.CniaoUtils
import com.google.gson.Gson
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

const val TAG = "NetRspKtx"
/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions: 基础的网络返回数据结构
 */

@Keep
data class BaseCniaoRsp(
    val code: Int, // 响应码
    val data: String?, // 响应数据内容 String?
    val message: String? // 响应数据的结果描述
) {

    companion object {
        const val SERVER_CODE_FAILED = 0 // 接口请求响应业务处理 失败
        const val SERVER_CODE_SUCCESS = 1 // 接口请求响应业务处理 成功 1001
        const val SERVER_CODE_SUCCESS1 = 1 // 接口请求响应业务处理 成功
    }

/*
|1| 成功|
|0 |失败|
|101| appid为空或者app不存在|
|102|签名错误|
|103|签名失效（已经使用过一次）|
|104|请求已失效（时间戳过期）|
|105|缺少必传参数|
|106|参数格式有误或者未按规则提交|
|201|缺少token|
|202|token无效/错误|
|203|token已过期|
|401|没有权限调用|
|501|数据库连接出错|
|502|读写数据库异常|
 */

}

inline fun <reified T> BaseCniaoRsp.toEntity(): T? {
    if (data == null) {
        LogUtils.e("server Response Json Ok,But data==null,$code,$message")
        return null
    }

    if (T::class.java.isAssignableFrom(String::class.java)) {
         return CniaoUtils.decodeData(this.data) as T
    }

    return kotlin.runCatching {
         GsonUtils.fromJson(CniaoUtils.decodeData(this.data), T::class.java)
    }.onFailure { e ->
        Log.e(TAG, "BaseCniaoRsp.toEntity error: ${e.toString()}")
        e.printStackTrace()
    }.getOrNull()
}

/**
 * 接口成功，但是业务返回code不是1或1001的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun BaseCniaoRsp.onBizError(crossinline block: (code: Int, message: String?) -> Unit): BaseCniaoRsp {
    contract {
        // callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    // if (code != BaseResponse.SERVER_CODE_SUCCESS) {
    Log.d("BaseCniaoRsp", "code: ${code}")
    if (code != BaseCniaoRsp.SERVER_CODE_SUCCESS) {
        block.invoke(code, message ?: "Error Message Null")
    }
    return this
}

/**
 * 接口成功且业务成功code==1的情况
 * crossinline关键字 只要标志了就不能进入return true函数快
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified T> BaseCniaoRsp.onBizOK(crossinline action: (code: Int, data: T?, message: String?)
-> Unit): BaseCniaoRsp {
    contract {
        // callsInPlace(action, InvocationKind.AT_MOST_ONCE)
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    // if (code == BaseResponse.SERVER_CODE_SUCCESS) {
    Log.d("BaseCniaoRsp", "code: ${code}")
    if (code == BaseCniaoRsp.SERVER_CODE_SUCCESS) {
        action.invoke(code, this.toEntity<T>(), message)
    }
    return this
}

// ---------------------------------------------------------以下的方法后面会用到↓-----------------------------------------

/**
 * TODO: BaseCniaoRsp -> BaseResponse
 * 这里表示网络请求成功，并得到业务服务器的响应
 * 将BaseCniaoRsp的对象，转化为需要的对象类型，也就是将body.string转为entity
 * @return 返回需要类型对象，可能为null，如果json解析失败的话
 * 加了reified才可以T::class.java
 */
inline fun <reified T> BaseResponse.toEntity(): T? {
    if (data == null) {
        LogUtils.e("server Response Json Ok,But data==null,$code,$message")
        return null
    }

    // todo 如果有需要的话在这里对返回的data数据进行解密然后再tojson fromjson

    // val decodeData = CnUtils.decodeData(data.toString())
    // gson不允许我们将json对象采用String,所以单独处理
    // if (T::class.java.isAssignableFrom(String::class.java)) {
    //     return decodeData as T
    // }

    //如果data不为空，先进行tojson处理再转化为T对象类型的entity string
    //传入LoginRsp就是LoginRsp  传入RegisterRsp就是RegisterRsp
    return kotlin.runCatching {
        // if (data is JsonObject){
        // } else if (data is JsonArray) {
        //     return GsonUtils.fromJson(Gson())
        // }
        GsonUtils.fromJson(Gson().toJson(data), T::class.java)
    }.onFailure { e ->
        e.printStackTrace() //Catch出错，报错
    }.getOrNull() //不为空
}

/**
 * 接口成功，但是业务返回code不是1或1001的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun BaseResponse.onBizError(crossinline block: (code: Int, message: String?) -> Unit): BaseResponse {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    if (code != BaseResponse.SERVER_CODE_SUCCESS1 && code != BaseResponse.SERVER_CODE_SUCCESS) { //同时不等于1和1001的时候执行if
        block.invoke(code, message ?: "Error Message Null") //返回错误码和错误信息
    }
    return this
}



/**
 * 接口成功且业务成功code==1的情况
 * crossinline关键字 只要标志了就不能进入return true函数快
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified T> BaseResponse.onBizOK(crossinline action: (code: Int, data: T?, message: String?) -> Unit): BaseResponse {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (code == BaseResponse.SERVER_CODE_SUCCESS || code == BaseResponse.SERVER_CODE_SUCCESS1) { //code == 1001或code == 1,成功
        action.invoke(code, this.toEntity<T>(), message) //返回成功码和解密之后的序列化对象
    }
    return this
}

/**
 * 扩展用于处理网络请求成功，网络接口请求成功，不代表业务逻辑成功
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onSuccess(action: R.() -> Unit):DataResult<R> {

    // 契约  kənˈtrækt
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (succeeded) action.invoke((this as DataResult.Success).data)

    return this
}

/**
 * 扩展用于处理网络接口请求失败
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onFailure(action: (exception: Throwable) -> Unit):DataResult<R> {

    // 契约  kənˈtrækt
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this is DataResult.Error) action.invoke(exception)

    return this
}