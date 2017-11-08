package com.ly.immortalblog.config.filter

import com.ly.immortalblog.domain.ImmortalException
import com.ly.immortalblog.domain.constant.AuthenticationConstatn
import com.ly.immortalblog.domain.constant.enums.ImmortalExceptionEnum
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 *@author ly
 */
class JWTAuthenticationFilter(authenticationManager: AuthenticationManager) :
        BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        if (HttpMethod.OPTIONS.matches(request.method)) {
            chain.doFilter(request, response)
        }else{
            val header = request.getHeader("Authorization") ?:
                    throw ImmortalException(ImmortalExceptionEnum.IMMORTAL_AUTHENTICATION_TOKEN_CREATE_FAIL)
            if (!header.startsWith("Bearer ")) {
                throw ImmortalException(ImmortalExceptionEnum.IMMORTAL_AUTHENTICATION_TOKEN_ILLEGAL_FAIL)
            }
            val authentication = getAuthentication(request)
            SecurityContextHolder.getContext().authentication = authentication
            chain.doFilter(request, response)
        }
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken {
        val token = request.getHeader("Authorization")
                ?: throw ImmortalException(ImmortalExceptionEnum.IMMORTAL_AUTHENTICATION_TOKEN_ILLEGAL_FAIL)
        // parse the token.
        val user = Jwts.parser()
                .setSigningKey(AuthenticationConstatn.SIGN_KEY)
                .parseClaimsJws(token.replace(AuthenticationConstatn.TOKEN_PREFIX, ""))
                .body
                .subject
                ?: throw ImmortalException(ImmortalExceptionEnum.IMMORTAL_AUTHENTICATION_TOKEN_ILLEGAL_FAIL)
        return UsernamePasswordAuthenticationToken(user, null, HashSet<GrantedAuthority>())
    }
}