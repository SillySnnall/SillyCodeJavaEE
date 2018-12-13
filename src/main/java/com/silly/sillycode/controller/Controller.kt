package com.silly.sillycode.controller

import com.silly.sillycode.entity.*
import com.silly.sillycode.service.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class Controller(
        private val userService: UserService,
        private val newsService: NewsService,
        private val bolgService: BolgService,
        private val commentService: CommentService,
        private val updateService: UpdateService
) {

    /**
     * 注册
     */
    @RequestMapping("/register")
    fun register(formUser: User): Data {
        return try {
            userService.register(formUser)
        } catch (e: Exception) {
            Data("注册失败", -1)
        }
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    fun login(formUser: User): Data {
        return try {
            userService.login(formUser)
        } catch (e: Exception) {
            Data("登陆失败", -1)
        }
    }

    /**
     * 微信小程序登陆
     */
    @RequestMapping("/wx_code_login")
    fun wxCodeLogin(formUser: User): Data {
        return try {
            userService.wxCodeLogin(formUser)
        } catch (e: Exception) {
            Data("微信小程序失败", -1)
        }
    }

    /**
     * 更改用户名
     */
    @RequestMapping("/change_username")
    fun changeUsername(formUser: User): Data {
        return try {
            userService.changeUsername(formUser)
        } catch (e: Exception) {
            Data("更改用户名失败", -1)
        }
    }

    /**
     * 更改密码
     */
    @RequestMapping("/change_password")
    fun changePassword(formUser: User): Data {
        return try {
            userService.changePassword(formUser)
        } catch (e: Exception) {
            Data("更改密码失败", -1)
        }
    }

    /**
     * 获取新闻数据
     */
    @RequestMapping("/news")
    fun news(fromPage: Page): Data {
        return try {
            newsService.newsList(fromPage)
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

    /**
     * 通过type获取bolg数据
     */
    @RequestMapping("/bolg_data")
    fun bolgData(fromPage: Page, fromBolgDetail: BolgDetail): Data {
        return try {
            bolgService.bolgData(fromPage, fromBolgDetail)
        } catch (e: Exception) {
            Data("博客数据异常", -1)
        }
    }

    /**
     * 通过bolgid获取博客
     */
    @RequestMapping("/bolg_one")
    fun bolgOne(fromBolgDetail: BolgDetail): Data {
        return try {
            bolgService.bolgOne(fromBolgDetail)
        } catch (e: Exception) {
            Data("博客数据异常", -1)
        }
    }

    /**
     * 发布bolg
     */
    @RequestMapping("/publish_bolg")
    fun addBolg(fromBolgDetail: BolgDetail): Data {
        return try {
            bolgService.publishBolg(fromBolgDetail)
        } catch (e: Exception) {
            Data("博客发布失败", -1)
        }
    }

    /**
     * 获取评论
     */
    @RequestMapping("/comment")
    fun comment(fromPage: Page, fromComment: Comment): Data {
        return try {
            commentService.comment(fromPage, fromComment)
        } catch (e: Exception) {
            Data("评论数据异常", -1)
        }
    }

    /**
     * 获取评论中的回复
     */
    @RequestMapping("/reply")
    fun reply(fromPage: Page, fromReplyComment: ReplyComment): Data {
        return try {
            commentService.reply(fromPage, fromReplyComment)
        } catch (e: Exception) {
            Data("回复数据异常", -1)
        }
    }

    /**
     * 发布评论
     */
    @RequestMapping("/publish_comment")
    fun publishComment(fromComment: Comment): Data {
        return try {
            commentService.publishComment(fromComment)
        } catch (e: Exception) {
            Data("发布评论失败", -1)
        }
    }

    /**
     * 回复评论
     */
    @RequestMapping("/reply_comment")
    fun replyComment(fromReplyComment: ReplyComment): Data {
        return try {
            commentService.replyComment(fromReplyComment)
        } catch (e: Exception) {
            Data("回复评论失败", -1)
        }
    }

    /**
     * 博客评论数目
     */
    @RequestMapping("/comment_count")
    fun commentCount(fromComment: Comment): Data {
        return try {
            commentService.commentCount(fromComment)
        } catch (e: Exception) {
            Data("获取评论数失败", -1)
        }
    }

    /**
     * 上传图片
     */
    @RequestMapping("/upload_img")
    fun uploadImg(@RequestParam("img_file") file: MultipartFile): Data {
        return try {
            updateService.uploadImg(file)
        } catch (e: Exception) {
            Data("上传图片失败", -1)
        }
    }

    /**
     * 获取全部分类
     */
    @RequestMapping("/type_all")
    fun typeAll(): Data {
        return try {
            bolgService.typeAll()
        } catch (e: Exception) {
            Data("获取分类失败", -1)
        }
    }

    /**
     * 添加分类
     */
    @RequestMapping("/add_type")
    fun addType(fromType: Type): Data {
        return try {
            bolgService.addType(fromType)
        } catch (e: Exception) {
            Data("添加分类失败", -1)
        }
    }
}
