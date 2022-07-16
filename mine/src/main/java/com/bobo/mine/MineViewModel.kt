package com.bobo.mine

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.bobo.common.base.BaseViewModel
import com.bobo.common.utils.NoDoubleClickUtil
import com.bobo.common.webview.WebViewActivity
import com.bobo.mine.repo.IMineResource
import com.bobo.service.repo.CniaoUserInfo

/**
 * Created by 公众号：IT波 on 2022/6/11 Copyright © Leon. All rights reserved.
 * Functions: api地址：http://yapi.54yct.com/project/24/interface/api/2127
 */
class MineViewModel(private val repo: IMineResource) : BaseViewModel() {

    // val liveUser = MutableLiveData<CniaoUserInfo>()

    // 用在userInfoFragment中
    val liveinfo = repo.liveUserInfo

    /**
     * 获取用户的信息
     */
    fun getUserInfo(token: String?) {
        serverAwait {
            repo.getUserInfo(token)
        }
    }

    // region 自己增加
    fun startToWebView(view: View) {

        // 避免用户快速点击开启多个界面
        if (NoDoubleClickUtil.isDoubleClick()) {
            return
        }

        WebViewActivity.openUrl(view.context, "https://mp.weixin.qq.com/s?__biz=MzI3NTc0NzI0NA==&mid=2247484083&" +
                "idx=1&sn=f4bf3111807a0e12e11ee8c1e05f8ae5&chksm=eb015a70dc76d366ba10f1ad35e407ea06ba0699ebe785eee04" +
                "49b69c516ef1241053cce2ea5&mpshare=1&scene=23&srcid=0710k1t3GHAg08ravxr5iqZm&sharer_sharetime=165741" +
                "8078343&sharer_shareid=205ec37e2b18cb79d8cf794b79891858#rd\n")
    }
    // endregion 自己增加
}