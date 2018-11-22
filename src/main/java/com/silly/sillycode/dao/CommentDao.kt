package com.silly.sillycode.dao

import com.silly.sillycode.entity.Comment
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentDao : JpaRepository<Comment, Long> {
    fun findByBolgId(bolgId: String, pageable: Pageable): List<Comment>
    fun findByBolgId(bolgId: String): List<Comment>
    fun findByCommentId(commentId: String): Comment?
}
