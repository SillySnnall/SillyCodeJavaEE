package com.silly.sillycode.service

import com.silly.sillycode.dao.BolgDetailDao
import com.silly.sillycode.dao.NewsDao
import com.silly.sillycode.dao.NewsDetailDao
import com.silly.sillycode.dao.TypeDao
import com.silly.sillycode.entity.*
import com.silly.sillycode.util.*
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort


@Service
class BolgService(
        private val bolgDetailDao: BolgDetailDao,
        private val typeDao: TypeDao
) {
    /**
     * bolg全部数据
     */
    fun bolgData(fromPage: Page): Data {
        val sort = Sort(Sort.Direction.DESC, "cdateTime")
        val pageable = PageRequest.of(fromPage.page, fromPage.size, sort)
        val data = bolgDetailDao.findAll(pageable)
        return Data(data)
    }

    /**
     * 添加bolg
     */
    fun publishBolg(fromBolgDetail: BolgDetail): Data {
        if (fromBolgDetail.headerImg.isEmpty()) return Data("标题图片为空", -1)
        if (fromBolgDetail.title.isEmpty()) return Data("标题为空", -1)
        if (fromBolgDetail.contents.isEmpty()) return Data("内容为空", -1)
        if (fromBolgDetail.userId.isEmpty()) return Data("用户id为空", -1)
        if (fromBolgDetail.type == 0) return Data("分类为空", -1)
        val findByAccount = bolgDetailDao.findByUserIdAndTitle(fromBolgDetail.userId, fromBolgDetail.title)
        if (findByAccount != null) return Data("文章已存在", -1)

        bolgDetailDao.save(BolgDetail(fromBolgDetail.headerImg, fromBolgDetail.title, fromBolgDetail.contents, fromBolgDetail.userId, cdateTime(), fromBolgDetail.type, cUUID()))
        return Data("博客发布成功")
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
        typeDao.save(fromType)
        return Data("分类添加成功")
    }
}
