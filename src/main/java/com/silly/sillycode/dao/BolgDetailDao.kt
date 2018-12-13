package com.silly.sillycode.dao

import com.silly.sillycode.entity.BolgDetail
import com.silly.sillycode.entity.Comment
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BolgDetailDao : JpaRepository<BolgDetail, Long> {
    fun findByUserIdAndTitle(userId: String, title: String): BolgDetail?
    fun findByBolgId(bolgId: String): BolgDetail?
    fun findByType(type: Int, pageable: Pageable): List<BolgDetail>
}
