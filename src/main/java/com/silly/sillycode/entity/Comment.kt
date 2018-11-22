package com.silly.sillycode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Comment(
        var bolgId: String = "",// 文章id
        var commentId: String = "",// 评论id
        var commentUserId: String = "",// 评论用户的id（谁评论的）
        var contents: String = "",// 评论内容
        var cdateTime: Long = 0,// 评论创建时间
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore // 过滤json返回字段
        var id: Long? = null,
        // 非数据库字段
        var username: String = "",// 评论人的名字
        var avatar: String = ""// 评论人的头像
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

//CREATE TABLE `comment` (
//`id` int(11) NOT NULL AUTO_INCREMENT,
//`bolg_id` varchar(32) NOT NULL COMMENT '博客id',
//`comment_id` varchar(32) NOT NULL COMMENT '评论id',
//`comment_user_id` varchar(17) NOT NULL COMMENT '评论用户的id',
//`contents` varchar(255) NOT NULL COMMENT '评论内容',
//`create_time` bigint(20) NOT NULL COMMENT '评论创建时间',
//PRIMARY KEY (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8;