package com.silly.sillycode.util

import org.apache.commons.codec.digest.DigestUtils
import java.util.*

/**
 * 生成创建时间
 */
fun cdateTime(): Long {
    return System.currentTimeMillis() / 1000
}

/**
 * 生成唯一的时间戳id，17位
 */
fun cTimeId(): String {
    val n = (1000 + Math.random() * (9999 - 1000 + 1)).toInt()
    val d = Date().time
    return "$n$d"
}

/**
 * 生成UUID,32位
 */
fun cUUID(): String {
    return UUID.randomUUID().toString().replace("-", "")
}

/**
 * MD5加密
 */
fun md5(text: String): String {
    return DigestUtils.md5Hex(text)
}

/**
 * 生成用户名
 */
fun cUsername(): String {
    return "用户${System.currentTimeMillis()}"
}