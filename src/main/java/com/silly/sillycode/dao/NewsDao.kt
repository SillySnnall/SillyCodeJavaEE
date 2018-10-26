package com.silly.sillycode.dao

import com.silly.sillycode.entity.News
import org.springframework.data.jpa.repository.JpaRepository

interface NewsDao : JpaRepository<News, Long> {
    fun findByNewsId(newsId: String): News?
}
