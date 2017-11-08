package com.ly.immortalblog.config.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ly.immortalblog.domain.ImmortalException
import com.ly.immortalblog.domain.constant.AuthenticationConstatn
import com.ly.immortalblog.domain.constant.ImmortalTimeConstant
import com.ly.immortalblog.domain.constant.enums.ImmortalExceptionEnum
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 *@author ly
 */
class JWTLoginFilter
    : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val loginMapInfo = jacksonObjectMapper().readValue<Map<String, Any>>(request.inputStream)
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                loginMapInfo["username"],
                loginMapInfo["password"],
                HashSet()
        ))
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse,
                                          chain: FilterChain, authResult: Authentication) {
        val token = Jwts.builder().setSubject((
                authResult.principal as org.springframework.security.core.userdetails.User).username)
                .setExpiration(Date(System.currentTimeMillis() + ImmortalTimeConstant.NORMAL_TOKEN_EXIST))
                .signWith(SignatureAlgorithm.HS512, AuthenticationConstatn.SIGN_KEY).compact()
                ?: throw ImmortalException(ImmortalExceptionEnum.IMMORTAL_AUTHENTICATION_TOKEN_CREATE_FAIL)
        response.setHeader("Authorization", AuthenticationConstatn.TOKEN_PREFIX + token)
    }
}