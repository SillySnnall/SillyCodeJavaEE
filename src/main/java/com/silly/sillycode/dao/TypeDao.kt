package com.silly.sillycode.dao

import com.silly.sillycode.entity.Type
import org.springframework.data.jpa.repository.JpaRepository

interface TypeDao : JpaRepository<Type, Long> {
    fun findByType(type: Int): Type?
}
