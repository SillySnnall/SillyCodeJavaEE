package com.silly.sillycode.dao

import com.silly.sillycode.entity.ReplyComment
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyCommentDao : JpaRepository<ReplyComment, Long> {
    fun findByCommentId(commentId: String, pageable: Pageable): List<ReplyComment>
}
