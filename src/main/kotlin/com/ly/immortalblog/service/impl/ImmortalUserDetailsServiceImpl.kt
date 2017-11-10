package com.ly.immortalblog.service.impl

import com.ly.immortalblog.domain.ImmortalException
import com.ly.immortalblog.domain.constant.enums.ImmortalResultEnum
import com.ly.immortalblog.domain.mapper.BasUserMapper
import com.ly.immortalblog.domain.model.BasUserExample
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 *@author ly
 */
@Service("userDetailsService")
class ImmortalUserDetailsServiceImpl(val basUserMapper: BasUserMapper,
                                     val basUserExample: BasUserExample) : UserDetailsService {
    override fun loadUserByUsername(userName: String?): UserDetails {
        basUserExample.or().andUsernameEqualTo(userName)
        val basUser = basUserMapper.selectByUsername(userName) ?:
                throw ImmortalException(ImmortalResultEnum.IMMORTAL_AUTHENTICATION_USERNAME_ERROR)
        return User(basUser.username, basUser.password, AuthorityUtils.createAuthorityList(basUser.role))
    }

}

