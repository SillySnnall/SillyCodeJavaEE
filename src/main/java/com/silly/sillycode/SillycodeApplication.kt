package com.silly.sillycode

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
open class SillycodeApplication

fun main(args: Array<String>) {
    SpringApplication.run(SillycodeApplication::class.java, *args)
}
