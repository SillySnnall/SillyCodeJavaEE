package com.silly.sillycode.util

import org.apache.commons.codec.digest.DigestUtils
import java.util.*
import java.io.IOException
import org.springframework.web.multipart.MultipartFile
import java.io.File


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

/**
 *
 * @param file 文件
 * @param path 文件存放路径
 * @param fileName 源文件名，如果为空则使用原文件名
 * @return
 */
fun upload(file: MultipartFile, path: String, fileName: String = ""): String {

    val dest = File(path)
    if (!dest.exists()) {
        dest.mkdirs()
    }
    val fileNameN =
            // 使用自动生成的文件名
            if (fileName.isEmpty()) "${cTimeId()}${getSuffix(file.originalFilename ?: "")}"
            // 使用原文件名
            else fileName

    val realPath = File("$path/$fileNameN")
    return try {
        //保存文件
        file.transferTo(realPath.absoluteFile)
        fileNameN
    } catch (e: IllegalStateException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
        ""
    } catch (e: IOException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
        ""
    }
}

/**
 * 获取文件后缀
 * @param fileName
 * @return
 */
fun getSuffix(fileName: String): String {
    return fileName.substring(fileName.lastIndexOf("."))
}