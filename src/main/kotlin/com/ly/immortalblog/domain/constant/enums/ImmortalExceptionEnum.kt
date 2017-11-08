package com.ly.immortalblog.domain.constant.enums

import com.ly.immortalblog.domain.BaseReturn

/**
 *@author ly
 */
enum class ImmortalExceptionEnum(override val code:Int, override val message:String):BaseReturn{
    IMMORTAL_SUCCESS(200,"success"),
    IMMORTAL_AUTHENTICATION_USERNAME_ERROR(400,"该用户尚未注册"),
    IMMORTAL_AUTHENTICATION_TOKEN_CREATE_FAIL(401,"创建token失败"),
    IMMORTAL_AUTHENTICATION_TOKEN_ILLEGAL_FAIL(402,"请求时请携带正确的认证信息");
}