package com.silly.sillycode.controller

import com.silly.sillycode.entity.Data
import com.silly.sillycode.entity.News
import com.silly.sillycode.entity.NewsDetail
import com.silly.sillycode.entity.User
import com.silly.sillycode.service.LoginService
import com.silly.sillycode.service.NewsPage
import com.silly.sillycode.service.NewsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
        private val loginService: LoginService,
        private val newsService: NewsService
) {
    /**
     * 登陆
     */
    @RequestMapping("/login")
    fun login(formUser: User): Data {
        return try {
            loginService.login(formUser)
        } catch (e: Exception) {
            Data("登陆失败", -1)
        }
    }

    /**
     * 获取新闻数据
     */
    @RequestMapping("/news")
    fun news(fromNewsPage: NewsPage): Data {
        return try {
            newsService.newsList(fromNewsPage)
        } catch (e: Exception) {
            Data("新闻数据异常", -1)
        }
    }

    /**
     * 获取新闻详情数据
     */
    @RequestMapping("/news_detail")
    fun newsDetail(fromNewsDetail: NewsDetail): Data {
        return try {
            newsService.newsDetail(fromNewsDetail)
        } catch (e: Exception) {
            Data("新闻详情数据异常", -1)
        }
    }
}
