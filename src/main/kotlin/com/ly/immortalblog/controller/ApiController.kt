package com.ly.immortalblog.controller

import com.ly.immortalblog.domain.ImmortalResult
import com.ly.immortalblog.domain.constant.enums.ImmortalResultEnum
import com.ly.immortalblog.domain.model.BasUser
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


/**
 * @author ly
 */
@RestController
@RequestMapping("/api")
class ApiController {
    @PostMapping("/user")
    fun getUserInfo(@RequestBody map:Map<String,Any> ,httpServletRequest: HttpServletRequest):ImmortalResult<Void>{
        for (entry in map) {
            println("${entry.key}:${entry.value}")
        }
        return ImmortalResult(ImmortalResultEnum.IMMORTAL_SUCCESS)
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

    @PostAuthorize("returnObject.username == principal.username")
    @GetMapping("test3")
    fun test3():BasUser{
        val a = BasUser()
        a.username =SecurityContextHolder.getContext().authentication.principal.toString()
        return a
    }

    @PostAuthorize("hasRole('ADMIN')")
    @GetMapping("test4")
    fun test4() {
        println(SecurityContextHolder.getContext().authentication.principal)
    }

}
