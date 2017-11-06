package com.ly.immortalblog.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author ly
 */
@RestController
@RequestMapping("/api")
class ApiController {
    @GetMapping("/user")
    //在这一步使用spring security 进行权限的认证(实际目的)认证通过后返回用户的角色信息
    fun getUserInfo() {

    }
}
