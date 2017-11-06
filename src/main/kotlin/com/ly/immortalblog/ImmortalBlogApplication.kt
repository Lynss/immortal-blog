package com.ly.immortalblog

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@MapperScan(basePackages = arrayOf("com.ly.immortalblog.domain.mapper"))
@ServletComponentScan
class ImmortalBlogApplication

fun main(args: Array<String>) {
    SpringApplication.run(ImmortalBlogApplication::class.java, *args)
}
