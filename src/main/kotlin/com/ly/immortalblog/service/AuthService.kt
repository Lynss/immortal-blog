package com.ly.immortalblog.service

import com.ly.immortalblog.domain.ImmortalResult
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

/**
 *@author ly
 */
interface AuthService {
    fun loginAndCreateToken(username:String,password:String):ImmortalResult<String>
}