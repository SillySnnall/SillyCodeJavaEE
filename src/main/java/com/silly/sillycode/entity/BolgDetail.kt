package com.silly.sillycode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class BolgDetail(
        var headerImg: String = "",// 文章标题图片
        var title: String = "",// 文章标题
        var contents: String = "",// 文章内容
        var userId: String = "",// 文章作者id
        var cdateTime: Long = 0,// 文章创建时间
        var type: Int = 0,// 文章分类
        var bolgId: String = "",// 文章唯一id
        var readCount: Int = 0,// 文章阅读人数
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore // 过滤json返回字段
        var id: Long? = null,
        // 非数据库字段
        var typeName: String = "" // 分类名
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

//CREATE TABLE `bolg_detail` (
//`id` int(11) NOT NULL AUTO_INCREMENT,
//`bolg_id` varchar(32) NOT NULL COMMENT '文章唯一id',
//`header_img` varchar(255) NOT NULL COMMENT '文章标题图片',
//`title` varchar(255) NOT NULL COMMENT '文章标题',
//`contents` text NOT NULL COMMENT '文章内容',
//`user_id` varchar(255) NOT NULL COMMENT '文章作者id',
//`cdate_time` bigint(20) NOT NULL COMMENT '文章创建时间',
//`type` int(11) NOT NULL COMMENT '文章分类',
//`read_count` int(255) NOT NULL COMMENT '文章阅读人数',
//PRIMARY KEY (`id`)
//) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;