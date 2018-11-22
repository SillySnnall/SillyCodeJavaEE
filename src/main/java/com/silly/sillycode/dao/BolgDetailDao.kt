package com.silly.sillycode.dao

import com.silly.sillycode.entity.BolgDetail
import org.springframework.data.jpa.repository.JpaRepository

interface BolgDetailDao : JpaRepository<BolgDetail, Long> {
    fun findByUserIdAndTitle(userId: String, title: String): BolgDetail?
    fun findByBolgId(bolgId: String): BolgDetail?
}
