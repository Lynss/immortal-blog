package com.ly.immortalblog.service.impl

import com.ly.immortalblog.domain.ImmortalResult
import com.ly.immortalblog.domain.constant.enums.ImmortalResultEnum
import com.ly.immortalblog.service.AuthService
import com.ly.immortalblog.utils.JWTUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 *@author ly
 */
@Service("authService")
class AuthServiceImpl(val authenticationManager: AuthenticationManager,
                      val jwtUtils: JWTUtils,
                      val userDetailsService: UserDetailsService) : AuthService {
    override fun loginAndCreateToken(username: String, password: String): ImmortalResult<String> {
        val upToken = UsernamePasswordAuthenticationToken(username, password)
        val authentication = authenticationManager.authenticate(upToken)
        SecurityContextHolder.getContext().authentication = authentication
        val jwtToken = jwtUtils.generateToken(userDetailsService.loadUserByUsername(username))
        return ImmortalResult(ImmortalResultEnum.IMMORTAL_SUCCESS, jwtToken)
    }
}

