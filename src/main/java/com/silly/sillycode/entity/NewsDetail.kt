package com.silly.sillycode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class NewsDetail(
        var newsId: String = "",// 新闻id
        var contents: String = "",// 内容
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore // 过滤json返回字段
        var id: Long? = null
){
        override fun toString(): String {
                return Gson().toJson(this)
        }
}

//CREATE TABLE `news_detail` (
//`id` int(11) NOT NULL AUTO_INCREMENT,
//`news_id` varchar(50) NOT NULL COMMENT '新闻id',
//`contents` longtext NOT NULL COMMENT '新闻内容',
//PRIMARY KEY (`id`)
//) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8