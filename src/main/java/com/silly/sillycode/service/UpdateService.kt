package com.silly.sillycode.service

import com.silly.sillycode.entity.*
import com.silly.sillycode.util.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class UpdateService {
    @Value("\${update.file}")
    private lateinit var updateFile: String
    @Value("\${main.url}")
    private lateinit var mainUrl: String

    /**
     * 上传图片
     */
    fun uploadImg(file: MultipartFile): Data {
        // 要上传的目标文件存放路径
        val localPath = "$updateFile/image"
        // 上传成功或者失败的提示
        val upload = upload(file, localPath)
        if (upload.isEmpty()) return Data("上传失败", -1)
        return Data("$mainUrl/image/$upload")
    }
}
