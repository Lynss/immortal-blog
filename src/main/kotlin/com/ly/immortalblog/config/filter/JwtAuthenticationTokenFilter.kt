package com.ly.immortalblog.config.filter

import com.ly.immortalblog.domain.constant.AuthenticationConstant
import com.ly.immortalblog.utils.JWTUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *@author ly
 */
@Service("jwtAuthenticationTokenFilter")
class JwtAuthenticationTokenFilter(private val jwtUtils:JWTUtils) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse,
                                  filterChain: FilterChain) {
        if(request.getHeader(AuthenticationConstant.TOKEN_HEADER) != null&&request.getHeader(AuthenticationConstant.TOKEN_HEADER).startsWith(AuthenticationConstant.TOKEN_PREFIX)){
            val authToken = request.getHeader(AuthenticationConstant.TOKEN_HEADER).substring(AuthenticationConstant
                    .TOKEN_PREFIX.length)
            if (jwtUtils.validateToken(authToken)&&SecurityContextHolder.getContext().authentication===null) {
                 val authentication=UsernamePasswordAuthenticationToken(
                         jwtUtils.getUsernameFromToken(authToken),
                         null,
                         jwtUtils.getAuthorities(authToken)
                 )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request,response)
    }
}