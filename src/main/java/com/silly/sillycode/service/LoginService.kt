package com.silly.sillycode.service

import com.silly.sillycode.dao.UserDao
import com.silly.sillycode.entity.Data
import com.silly.sillycode.entity.User
import com.silly.sillycode.util.AppID
import com.silly.sillycode.util.AppSecret
import com.silly.sillycode.util.WxKeyUrl
import com.silly.sillycode.util.post
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap

// {"errcode":40013,"errmsg":"invalid appid, hints: [ req_id: 1qPmqA02272064 ]"}
// {session_key: "z0AGUqFARIPogFlTF8SxEQ==", openid: "o7NQG0UosuXYZvNlV4FGvEyQKnVo"}
data class UserKey(var openid: String = "", var session_key: String = "")

@Service
class LoginService(private val userDao: UserDao) {
    /**
     * 登陆
     */
    fun login(formUser: User): Data {
        if (formUser.account.isEmpty()) return Data("account is empty", -1)
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
        val user = userDao.save(User(key.openid, formUser.username, key.session_key, 0, 0, formUser.avatar, System.currentTimeMillis() / 1000))
        return Data(user)
    }
}
