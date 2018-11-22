package com.silly.sillycode.service

import com.silly.sillycode.dao.UserDao
import com.silly.sillycode.entity.Data
import com.silly.sillycode.entity.User
import com.silly.sillycode.util.*
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap

// {"errcode":40013,"errmsg":"invalid appid, hints: [ req_id: 1qPmqA02272064 ]"}
// {session_key: "z0AGUqFARIPogFlTF8SxEQ==", openid: "o7NQG0UosuXYZvNlV4FGvEyQKnVo"}
data class UserKey(var openid: String = "", var session_key: String = "")

@Service
class UserService(private val userDao: UserDao) {
    /**
     * 微信小程序登陆
     */
    fun wxCodeLogin(formUser: User): Data {
        if (formUser.account.isEmpty()) return Data("帐号为空", -1)
        var findByAccount = userDao.findByAccount(formUser.account)
        if (findByAccount != null) return Data(findByAccount)

        // 微信首次登陆
        val key = post(WxKeyUrl,
                UserKey::class.java,
                LinkedMultiValueMap<String, String>().apply {
                    add("appid", AppID)
                    add("secret", AppSecret)
                    add("js_code", formUser.account)
                    add("grant_type", "authorization_code")
                }
        )
        findByAccount = userDao.findByAccount(key.openid)
        if (findByAccount != null) {
            return Data(findByAccount)
        }
        if (key.openid.isEmpty() || formUser.username.isEmpty() || key.session_key.isEmpty()) return Data("登录失败", -1)
        val user = userDao.save(User(key.openid, formUser.username, key.session_key, 0, 0, formUser.avatar, cdateTime(), cTimeId()))
        return Data(user)
    }

    /**
     * 普通登录
     */
    fun login(formUser: User): Data {
        if (formUser.account.isEmpty()) return Data("帐号为空", -1)
        if (formUser.password.isEmpty()) return Data("密码为空", -1)

        val findByAccount = userDao.findByAccount(formUser.account)
        return if (findByAccount == null) Data("用户不存在", -1)
        else Data(findByAccount)
    }

    /**
     * 注册
     */
    fun register(formUser: User): Data {
        if (formUser.account.isEmpty()) return Data("帐号为空", -1)
        if (formUser.password.isEmpty()) return Data("密码为空", -1)
        if (formUser.accountType == 0) return Data("登录方式为空", -1)

        val findByAccount = userDao.findByAccount(formUser.account)
        if (findByAccount != null) return Data("用户已存在", -1)
        val user = userDao.save(User(formUser.account, cUsername(), formUser.password, formUser.accountType, formUser.role, formUser.avatar, cdateTime(), cTimeId()))
        return Data(user)
    }

    /**
     * 更改用户名
     */
    fun changeUsername(formUser: User): Data {
        if (formUser.username.isEmpty()) return Data("用户名为空", -1)
        if (formUser.userId.isEmpty()) return Data("用户ID为空", -1)
        val findByUserId = userDao.findByUserId(formUser.userId) ?: return Data("用户不存在", -1)
        findByUserId.username = formUser.username
        val user = userDao.save(findByUserId)
        return Data(user)
    }

    /**
     * 更改密码
     */
    fun changePassword(formUser: User): Data {
        if (formUser.password.isEmpty()) return Data("密码为空", -1)
        if (formUser.userId.isEmpty()) return Data("用户ID为空", -1)
        val findByUserId = userDao.findByUserId(formUser.userId) ?: return Data("用户不存在", -1)
        findByUserId.password = formUser.password
        val user = userDao.save(findByUserId)
        return Data(user)
    }

}
