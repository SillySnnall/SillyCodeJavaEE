package com.silly.sillycode.service

import com.silly.sillycode.dao.BolgDetailDao
import com.silly.sillycode.dao.NewsDao
import com.silly.sillycode.dao.NewsDetailDao
import com.silly.sillycode.dao.TypeDao
import com.silly.sillycode.entity.*
import com.silly.sillycode.util.*
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.io.File


@Service
class BolgService(
        private val bolgDetailDao: BolgDetailDao,
        private val typeDao: TypeDao
) {

    @Value("\${update.file}")
    private lateinit var updateFile: String

    @Value("\${main.url}")
    private lateinit var mainUrl: String

    /**
     * bolg全部数据
     */
    fun bolgData(fromPage: Page): Data {
        val sort = Sort(Sort.Direction.DESC, "cdateTime")
        val pageable = PageRequest.of(fromPage.page, fromPage.size, sort)
        val datas = bolgDetailDao.findAll(pageable)
        for (data in datas) {
            data.contents = decoderBase64(data.contents)
            data.typeName = typeDao.findByType(data.type)?.typeName ?: "未分类"
        }
        return Data(datas)
    }

    /**
     * 通过bolgid获取博客
     */
    fun bolgOne(fromBolgDetail: BolgDetail): Data {
        if (fromBolgDetail.bolgId.isEmpty()) return Data("文章ID为空", -1)
        val data = bolgDetailDao.findByBolgId(fromBolgDetail.bolgId) ?: return Data("文章不存在", -1)
        data.contents = decoderBase64(data.contents)
        return Data(data)
    }

    /**
     * 添加bolg
     */
    fun publishBolg(fromBolgDetail: BolgDetail): Data {
        if (fromBolgDetail.headerImg.isEmpty()) return Data("标题图片为空", -1)
        if (fromBolgDetail.title.isEmpty()) return Data("标题为空", -1)
        if (fromBolgDetail.type == 0) return Data("分类为空", -1)
        if (fromBolgDetail.contents.isEmpty()) return Data("内容为空", -1)
        if (fromBolgDetail.userId.isEmpty()) return Data("用户id为空", -1)

        val findByAccount = bolgDetailDao.findByUserIdAndTitle(fromBolgDetail.userId, fromBolgDetail.title)
        if (findByAccount != null) return Data("文章已存在", -1)

        // bolg内容做Base64加密
        fromBolgDetail.contents = encodeBase64(fromBolgDetail.contents)

        // 保存bolg数据到数据库
        val bolgDetail = BolgDetail(fromBolgDetail.headerImg, fromBolgDetail.title, fromBolgDetail.contents, fromBolgDetail.userId, cdateTime(), fromBolgDetail.type, cUUID())
        bolgDetailDao.save(bolgDetail)
        return Data(bolgDetail)
    }


    /**
     * 获取全部分类
     */
    fun typeAll(): Data {
        val data = typeDao.findAll()
        return Data(data)
    }

    /**
     * 添加分类
     */
    fun addType(fromType: Type): Data {
        val sort = Sort(Sort.Direction.DESC, "id")
        val findAll = typeDao.findAll(sort)
        if (findAll.isEmpty()) fromType.type = 1 else fromType.type = (findAll[0].id ?: 0+1).toInt()
        typeDao.save(fromType)
        return Data("分类添加成功")
    }


    /**
     * Base64图片转Url
     */
    private fun imgBase64ToUrl(base64S: String): String {
        try {
            val doc = Jsoup.parse(base64S)
            val links = doc.getElementsByTag("img")
            for (link in links) {
                val imgUrl = GenerateImage(link.attr("src"), "$updateFile/image")
                link.attr("src", "$mainUrl/image/$imgUrl")
                link.attr("style", "max-width: 1080px;")
            }
            val text = doc.toString().replace("\n", "").replace("<html>", "")
                    .replace("</html>", "").replace("<head></head>", "")
                    .replace("<body>", "").replace("</body>", "")
                    .replace("  ", "").trim()
            return text
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}
