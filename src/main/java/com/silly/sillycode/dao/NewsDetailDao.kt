package com.silly.sillycode.dao

import com.silly.sillycode.entity.NewsDetail
import org.springframework.data.jpa.repository.JpaRepository

interface NewsDetailDao : JpaRepository<NewsDetail, Long> {
    fun findByNewsId(newsId: String): NewsDetail?
}
