package com.ly.immortalblog.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

/**
 *@author ly
 */
@Configuration
class DispatcherConfig:AbstractAnnotationConfigDispatcherServletInitializer(){
    override fun getRootConfigClasses(): Array<Class<*>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getServletMappings(): Array<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getServletConfigClasses(): Array<Class<*>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}