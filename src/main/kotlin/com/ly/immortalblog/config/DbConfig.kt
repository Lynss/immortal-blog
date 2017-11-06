package  com.ly.immortalblog.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

class DbConfig {
    @Primary
    @Bean("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    fun dataSource() = DataSourceBuilder.create().build()!!
}