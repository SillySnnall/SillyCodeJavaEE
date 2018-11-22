package com.silly.sillycode.service

import com.silly.sillycode.dao.*
import com.silly.sillycode.entity.*
import com.silly.sillycode.util.*
import org.springframework.stereotype.Service
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort


@Service
class CommentService(
        private val commentDao: CommentDao,
        private val replyCommentDao: ReplyCommentDao,
        private val bolgDetailDao: BolgDetailDao
) {
    /**
     * 通过bolgId来查询当前博客的评论
     */
    fun comment(fromPage: Page, fromComment: Comment): Data {
        val sort = Sort(Sort.Direction.DESC, "cdateTime")
        val pageable = PageRequest.of(fromPage.page, fromPage.size, sort)
        val data = commentDao.findByBolgId(fromComment.bolgId, pageable)
        return Data(data)
    }

    /**
     * 通过commentId来查询当前评论的回复
     */
    fun reply(fromPage: Page, fromReplyComment: ReplyComment): Data {
        val sort = Sort(Sort.Direction.DESC, "cdateTime")
        val pageable = PageRequest.of(fromPage.page, fromPage.size, sort)
        val data = replyCommentDao.findByCommentId(fromReplyComment.commentId, pageable)
        return Data(data)
    }

    /**
     * 发布评论
     */
    fun publishComment(fromComment: Comment): Data {
        if (fromComment.bolgId.isEmpty()) return Data("博客ID为空", -1)
        if (fromComment.commentUserId.isEmpty()) return Data("评论用户ID为空", -1)
        if (fromComment.contents.isEmpty()) return Data("内容为空", -1)

        bolgDetailDao.findByBolgId(fromComment.bolgId) ?: return Data("文章不存在", -1)

        commentDao.save(Comment(fromComment.bolgId, cUUID(), fromComment.commentUserId, fromComment.contents, cdateTime()))
        return Data("发布评论成功")
    }

    /**
     * 回复评论
     */
    fun replyComment(fromReplyComment: ReplyComment): Data {
        if (fromReplyComment.commentId.isEmpty()) return Data("评论ID为空", -1)
        if (fromReplyComment.replyUserId.isEmpty()) return Data("回复用户ID为空", -1)
        if (fromReplyComment.targetUserId.isEmpty()) return Data("目标用户ID为空", -1)
        if (fromReplyComment.contents.isEmpty()) return Data("内容为空", -1)

        commentDao.findByCommentId(fromReplyComment.commentId) ?: return Data("评论不存在", -1)

        replyCommentDao.save(ReplyComment(fromReplyComment.commentId, cUUID(), fromReplyComment.replyUserId,
                fromReplyComment.targetUserId, fromReplyComment.contents, cdateTime()))
        return Data("回复评论成功")
    }

    /**
     * 评论数目统计
     */
    fun commentCount(fromComment: Comment): Data {
        val data = commentDao.findByBolgId(fromComment.bolgId).count()
        return Data(data)
    }

}
