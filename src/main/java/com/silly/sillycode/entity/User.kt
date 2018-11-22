package com.silly.sillycode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @JsonIgnore
        var account: String = "",// 用户登录名，唯一
        var username: String = "",// 用户名
        @JsonIgnore
        var password: String = "",// 用户密码，MD5加密
        var accountType: Int = 0,// 用户登录方式（1-普通，2-微信，3-QQ）
        var role: Int = 0,// 角色（0-普通用户，1-管理员）
        var avatar: String = "",// 用户头像
        @JsonIgnore
        var createTime: Long = 0,// 用户创建时间戳
        var userId: String = "",// 用户唯一id
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore // 过滤json返回字段
        var id: Long? = null
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

//CREATE TABLE `user` (
//`id` int(11) NOT NULL AUTO_INCREMENT,
//`user_id` varchar(17) NOT NULL COMMENT '用户唯一id',
//`account` varchar(40) NOT NULL COMMENT '用户登录名，唯一',
//`username` varchar(50) NOT NULL COMMENT '用户名',
//`password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
//`account_type` int(4) NOT NULL COMMENT '用户登录方式（0-微信，1-普通，2-QQ）',
//`role` int(4) NOT NULL COMMENT '角色（0-普通用户，1-管理员）',
//`create_time` int(10) NOT NULL COMMENT '用户创建时间戳',
//`avatar` varchar(255) NOT NULL COMMENT '用户头像',
//PRIMARY KEY (`id`) USING BTREE
//) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;