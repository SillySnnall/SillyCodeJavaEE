package com.silly.sillycode.timer

import com.silly.sillycode.util.delFolder
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.File
import java.io.File.separator


/**
 * 删除文件的定时器
 */
@Component
class DelFileTimer {

    @Value("\${update.file.silly-temp}")
    private lateinit var sillyTemp: String

    @Scheduled(cron = "0 0 4 ? * *")
    fun scheduled1() {
        task()
    }

    /**
     * 执行的任务
     */
    private fun task() {
        delFolder(sillyTemp)
        println("定时器任务（DelFileTimer） : 删除了临时文件夹（sillyTemp）")
    }
}
