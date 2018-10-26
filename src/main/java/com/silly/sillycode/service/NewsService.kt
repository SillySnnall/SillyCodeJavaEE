package com.silly.sillycode.service

import com.silly.sillycode.dao.NewsDao
import com.silly.sillycode.dao.NewsDetailDao
import com.silly.sillycode.entity.Data
import com.silly.sillycode.entity.NewsDetail
import com.silly.sillycode.entity.User
import com.silly.sillycode.util.AppID
import com.silly.sillycode.util.AppSecret
import com.silly.sillycode.util.WxKeyUrl
import com.silly.sillycode.util.post
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort


data class NewsPage(
        var size: Int = 30, // 每页显示条数
        var page: Int = 0 //当前页数
)

@Service
class NewsService(
        private val newsDao: NewsDao,
        private val newsDetailDao: NewsDetailDao
) {
    /**
     * 新闻列表
     */
    fun newsList(fromNewsPage: NewsPage): Data {
        val sort = Sort(Sort.Direction.DESC, "cdateTime")
        val pageable = PageRequest.of(fromNewsPage.page, fromNewsPage.size, sort)
        val newsData = newsDao.findAll(pageable)
        return Data(newsData)
    }

    /**
     * 新闻详情
     */
    fun newsDetail(fromNewsDetail: NewsDetail): Data {
        val newsDetailData = newsDetailDao.findByNewsId(fromNewsDetail.newsId) ?: return Data("新闻不存在", -1)
        return Data(newsDetailData)
    }
}
