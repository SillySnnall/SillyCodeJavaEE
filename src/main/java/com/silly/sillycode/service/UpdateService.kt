package com.silly.sillycode.service

import com.silly.sillycode.entity.*
import com.silly.sillycode.util.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class UpdateService {
    @Value("\${update.file.silly-temp}")
    private lateinit var sillyTemp: String
    @Value("\${main.url}")
    private lateinit var mainUrl: String

    /**
     * 上传图片
     */
    fun uploadImg(file: MultipartFile): Data {
        val upload = upload(file, sillyTemp)
        if (upload.isEmpty()) return Data("上传失败", -1)
        return Data("$mainUrl/sillyTemp/$upload")
    }
}
