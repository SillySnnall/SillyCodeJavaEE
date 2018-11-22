package com.silly.sillycode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ReplyComment(
        var commentId: String = "",// 评论id
        var replyId: String = "",// 回复id
        var replyUserId: String = "",// 回复用户的id（谁回复的）
        var targetUserId: String = "",// 目标用户的id（回复谁的）
        var contents: String = "",// 回复内容
        var cdateTime: Long = 0,// 回复创建时间
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore // 过滤json返回字段
        var id: Long? = null,
        // 非数据库字段
        var username: String = "",// 回复用户的名字
        var avatar: String = "",// 回复用户的头像
        var targetUsername: String = "" // 目标用户的名字
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

//CREATE TABLE `reply_comment` (
//`id` int(11) NOT NULL,
//`comment_id` varchar(32) NOT NULL COMMENT '评论id',
//`reply_id` varchar(32) NOT NULL COMMENT '回复id',
//`reply_user_id` varchar(17) NOT NULL COMMENT '回复用户的id（谁回复的）',
//`target_user_id` varchar(17) NOT NULL COMMENT '目标用户的id（回复谁的）',
//`contents` varchar(255) NOT NULL COMMENT '回复内容',
//`create_time` bigint(20) NOT NULL COMMENT '回复创建时间',
//PRIMARY KEY (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8;