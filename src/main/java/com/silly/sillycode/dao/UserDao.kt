package com.silly.sillycode.dao

import com.silly.sillycode.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserDao : JpaRepository<User, Long> {
    fun findByAccount(account: String): User?
    fun findByUserId(userId: String): User?
}
