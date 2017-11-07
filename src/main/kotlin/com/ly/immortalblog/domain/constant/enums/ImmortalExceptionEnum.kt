package com.ly.immortalblog.domain.constant.enums

import com.ly.immortalblog.domain.BaseReturn

/**
 *@author ly
 */
enum class ImmortalExceptionEnum(override val code:Int, override val message:String):BaseReturn{
    IMMORTAL_SUCCESS(200,"success"),
    IMMORTAL_AUTHENTICATION_USERNAME_ERROR(400,"该用户尚未注册");
}