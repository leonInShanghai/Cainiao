package com.bobo.login

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.bobo.common.base.BaseActivity
import com.bobo.common.ktx.context
import com.bobo.common.network.config.SP_KEY_USER_TOKEN
import com.bobo.common.utils.CniaoSpUtils
import com.bobo.login.databinding.ActivityLoginBinding
import com.bobo.login.net.RegisterRsp
import com.bobo.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel



/**
 * Created by 公众号：IT波 on 2022/6/1 Copyright © Leon. All rights reserved.
 * Functions: 登陆Activity
 * 测试账号 18648957777
 * 测试账号 cn5123456
 */
@Route(path = "/login/login")
class LoginActivity: BaseActivity<ActivityLoginBinding>() {

     private val viewModel: LoginViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun initView() {
        mBinding.apply {
            vm = viewModel
            // 点击事件
            mtoolbarLogin.setNavigationOnClickListener{finish()}
            tvRegisterLogin.setOnClickListener {
                Toast.makeText(this@LoginActivity, "课程项目未实现账号注册功能", Toast.LENGTH_SHORT).show()
            }
            // tvForgotPwdLogin.setOnClickListener {
            //     Toast.makeText(this@LoginActivity, "课程项目未实现忘记密码功能", Toast.LENGTH_SHORT).show()
            // }
            // ivWechatLogin.setOnClickListener {
            //     Toast.makeText(this@LoginActivity, "课程项目未实现微信登录功能", Toast.LENGTH_SHORT).show()
            // }
            // ivQqLogin.setOnClickListener {
            //     Toast.makeText(this@LoginActivity, "课程项目未实现QQ登录功能", Toast.LENGTH_SHORT).show()
            // }
            // ivWeiboLogin.setOnClickListener {
            //     Toast.makeText(this@LoginActivity, "课程项目未实现微博登录功能", Toast.LENGTH_SHORT).show()
            // }
        }
    }

    override fun initConfig() {
        super.initConfig()
        viewModel.apply {
            liveRegisterRsp.observerKt {
                if (it?.is_register == RegisterRsp.FLAG_IS_REGISTERED) {
                    repoLogin()
                }
            }
            liveLoginRsp.observerKt { rsp ->
                Toast.makeText(this@LoginActivity, "登录结果：${rsp.toString()}", Toast.LENGTH_LONG).show()
                // 判断rsp不为空
                rsp?.let {
                    // 同步到room数据库，登录状态
                    CniaoDbHelper.insertUserInfo(context, rsp)
                    // 保存token
                    CniaoSpUtils.put(SP_KEY_USER_TOKEN, rsp.token)
                }
                // 关闭Activity
                finish()
            }
        }
    }
}

