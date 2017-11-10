package com.ly.immortalblog.utils

import com.ly.immortalblog.domain.ImmortalException
import com.ly.immortalblog.domain.constant.AuthenticationConstant
import com.ly.immortalblog.domain.constant.ImmortalTimeConstant
import com.ly.immortalblog.domain.constant.enums.ImmortalResultEnum
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.DefaultClaims
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*


/**
 *@author ly
 */

@Component("jwtUtils")
class JWTUtils {
    private fun getClaimsFromToken(token: String) = Jwts.parser()
            .setSigningKey(AuthenticationConstant.SIGN_KEY)
            .parseClaimsJws(token)
            .body!!

    fun getUsernameFromToken(token: String): String {
        try {
            return getClaimsFromToken(token).subject!!
        } catch (e: Exception) {
            throw ImmortalException(ImmortalResultEnum.IMMORTAL_AUTHENTICATION_TOKEN_ILLEGAL_FAIL)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun getAuthorities(token: String): List<GrantedAuthority>? {
        val tempNotNull = getClaimsFromToken(token)[AuthenticationConstant.TOKEN_AUTHORITIES] ?: return null
        return (tempNotNull as Collection<*>).map{ SimpleGrantedAuthority(it.toString())}
    }

    fun generateToken(userDetails: UserDetails,
                      signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS512,
                      existTime: Long = ImmortalTimeConstant.NORMAL_TOKEN_EXIST): String {
        val claims = DefaultClaims()
        claims.put(AuthenticationConstant.TOKEN_AUTHORITIES , userDetails.authorities)
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.username)
                .setExpiration(Date(System.currentTimeMillis() + existTime))
                .signWith(signatureAlgorithm, AuthenticationConstant.SIGN_KEY)
                .compact()
    }

    fun validateToken(token: String) = Date().before(getClaimsFromToken(token).expiration)
}