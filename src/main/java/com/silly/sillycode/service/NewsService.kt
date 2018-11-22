package com.silly.sillycode.service

import com.silly.sillycode.dao.NewsDao
import com.silly.sillycode.dao.NewsDetailDao
import com.silly.sillycode.entity.*
import com.silly.sillycode.util.AppID
import com.silly.sillycode.util.AppSecret
import com.silly.sillycode.util.WxKeyUrl
import com.silly.sillycode.util.post
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Service
class NewsService(
        private val newsDao: NewsDao,
        private val newsDetailDao: NewsDetailDao
) {
    /**
     * 新闻列表
     */
    fun newsList(fromPage: Page): Data {
        val sort = Sort(Sort.Direction.DESC, "cdateTime")
        val pageable = PageRequest.of(fromPage.page, fromPage.size, sort)
        val data = newsDao.findAll(pageable)
        return Data(data)
    }

    /**
     * 新闻详情
     */
    fun newsDetail(fromNewsDetail: NewsDetail): Data {
        val data = newsDetailDao.findByNewsId(fromNewsDetail.newsId) ?: return Data("新闻不存在", -1)
        return Data(data)
    }
}
