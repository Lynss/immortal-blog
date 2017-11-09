package com.ly.immortalblog.controller

import com.ly.immortalblog.domain.constant.Urls
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *@author ly
 */
@RestController
@RequestMapping(Urls.AUTH_API)
class AuthController {
    @PostMapping(Urls.LOGIN)
    fun loginAndCreateToken(@RequestBody map:Map<String,Any>) {

    }
}