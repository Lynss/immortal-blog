package com.ly.immortalblog.controller

import com.ly.immortalblog.domain.ImmortalResult
import com.ly.immortalblog.domain.constant.enums.ImmortalExceptionEnum
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


/**
 * @author ly
 */
@RestController
@RequestMapping("/api")
class ApiController {
    @PostMapping("/login")
    fun getUserInfo(@RequestBody map:Map<String,Any> ,httpServletRequest: HttpServletRequest):ImmortalResult<Void>{
        for (entry in map) {
            println("${entry.key}:${entry.value}")
        }
        return ImmortalResult(ImmortalExceptionEnum.IMMORTAL_SUCCESS)
    }
}
