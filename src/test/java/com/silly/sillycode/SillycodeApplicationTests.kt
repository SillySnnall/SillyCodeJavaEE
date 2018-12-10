package com.silly.sillycode

import com.silly.sillycode.dao.BolgDetailDao
import com.silly.sillycode.dao.CommentDao
import com.silly.sillycode.dao.TypeDao
import com.silly.sillycode.dao.UserDao
import com.silly.sillycode.entity.BolgDetail
import com.silly.sillycode.entity.Comment
import com.silly.sillycode.entity.Type
import com.silly.sillycode.entity.User
import com.silly.sillycode.service.BolgService
import com.silly.sillycode.service.CommentService
import com.silly.sillycode.service.NewsService
import com.silly.sillycode.service.UserService
import com.silly.sillycode.util.*
import org.jsoup.Jsoup
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.junit4.SpringRunner
import java.io.File
import java.lang.Integer.parseInt
import java.util.*
import javax.imageio.ImageIO
import jdk.nashorn.tools.ShellFunctions.input


@RunWith(SpringRunner::class)
@SpringBootTest
class SillycodeApplicationTests(

) {

    @Autowired
    private lateinit var bolgDetailDao: BolgDetailDao
    @Autowired
    private lateinit var newsService: NewsService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var commentService: CommentService

    @Autowired
    private lateinit var bolgService: BolgService

    @Autowired
    private lateinit var typeDao: TypeDao


    @Test
    fun contextLoads() {
//        bolgDetailDao.save(BolgDetail("文绉绉","我是内容","78281542800848835", cdateTime(),1, cUUID()))
//        userService.register(User("test$i",cUsername(), md5("test$i"),1,0,"", cdateTime(), cTimeId()))

//        val findAll = userDao.findAll()
//        for (user in findAll) {
//            bolgDetailDao.save(BolgDetail("文绉绉${user.userId}", "我是内容${user.userId}", user.userId, cdateTime(), 1, cUUID()))
//        }

//        val login = userService.login(User("test1", password = md5("test1")))
//        println(login)

//        commentService.publishComment(Comment())
//        commentService.commentCount(Comment())


//        bolgService.addType(Type("Android"))
//        bolgService.addType(Type("ios"))
//        bolgService.addType(Type("JavaEE"))
    }

    @Value("\${update.file}")
    private lateinit var updateFile: String

    @Value("\${main.url}")
    private lateinit var mainUrl: String
}
