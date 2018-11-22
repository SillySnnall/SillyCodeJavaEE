package com.silly.sillycode.dao

import com.silly.sillycode.entity.News
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Pageable

interface NewsDao : JpaRepository<News, Long> {
    fun findByNewsId(newsId: String): News?
}
