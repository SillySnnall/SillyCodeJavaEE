package com.silly.sillycode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class News(
        var newsId: String = "",// 新闻id
        var title: String = "",// 标题
        var imgurl: String = "",// 图片
        var cdateTime: Long = 0L,// 创建时间
        var source: String = "",// 出版社
        var summary: String = "",// 摘要
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore // 过滤json返回字段
        var id: Long? = null) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

//CREATE TABLE `news` (
//`id` int(11) NOT NULL AUTO_INCREMENT,
//`news_id` varchar(50) NOT NULL COMMENT '新闻id',
//`title` varchar(255) NOT NULL COMMENT '新闻标题',
//`imgurl` varchar(255) NOT NULL COMMENT '新闻图片',
//`cdate_time` varchar(20) NOT NULL COMMENT '新闻创建时间',
//`source` varchar(255) NOT NULL COMMENT '新闻出版社',
//`summary` varchar(255) NOT NULL COMMENT '新闻摘要',
//PRIMARY KEY (`id`)
//) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8