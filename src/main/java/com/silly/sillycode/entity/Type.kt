package com.silly.sillycode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Type(
        var typeName: String = "",// 文章分类名字
        var type: Int = 0,// 文章分类,代码自动生成
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore // 过滤json返回字段
        var id: Long? = null
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

//CREATE TABLE `type` (
//`id` int(11) NOT NULL,
//`type_name` varchar(255) NOT NULL COMMENT '分类名字',
//`type` int(11) NOT NULL COMMENT '分类标识符',
//PRIMARY KEY (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8;