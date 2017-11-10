package com.ly.immortalblog.controller

import com.ly.immortalblog.domain.ImmortalResult
import com.ly.immortalblog.domain.constant.Urls
import com.ly.immortalblog.service.AuthService
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

/**
 *@author ly
 */
@RestController
@RequestMapping(Urls.AUTH_API)
class AuthController(val authService: AuthService) {
    @PostMapping(Urls.LOGIN)
    fun loginAndCreateToken(@RequestBody map: Map<String, Any>): ImmortalResult<String> {
        return authService.loginAndCreateToken(map["username"].toString(), map["password"].toString())
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("test1")
    fun test1() {
        println(SecurityContextHolder.getContext().authentication.principal)
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("test2")
    fun test2() {
        println(SecurityContextHolder.getContext().authentication.principal)
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ADMIN')")
    @GetMapping("test3")
    fun test3(): User {
        println(SecurityContextHolder.getContext().authentication.principal)
        val a = User(SecurityContextHolder.getContext().authentication.principal.toString(),"",ArrayList())
        return a
    }

    @PostAuthorize("hasRole('ADMIN')")
    @GetMapping("test4")
    fun test4() {
        println(SecurityContextHolder.getContext().authentication.principal)
    }
}