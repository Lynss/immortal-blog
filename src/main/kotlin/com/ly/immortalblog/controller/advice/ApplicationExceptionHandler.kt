package com.ly.immortalblog.controller.advice

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ApplicationExceptionHandler{
    @ExceptionHandler(Exception::class)
    fun exceptionHandler() {
        println("handle success")
    }
}
