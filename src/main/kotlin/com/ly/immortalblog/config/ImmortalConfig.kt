package  com.ly.immortalblog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@Configuration
@Import(DbConfig::class,MybatisConfig::class,SecurityConfig::class,CorsConfig::class)
@ComponentScan(basePackages = arrayOf("com.ly.immortalblog.utils","com.ly.immortalblog.config.filter"))
@EnableScheduling
class ImmortalConfig {

    /**
     *spring-task多线程配置
     */
    @Bean
    fun taskRegistrar():ScheduledTaskRegistrar{
        val scheduledTaskRegistrar = ScheduledTaskRegistrar()
        scheduledTaskRegistrar.setScheduler(taskExecutor())
        return scheduledTaskRegistrar
    }

    @Bean
    fun taskExecutor(): Executor {
        return Executors.newScheduledThreadPool(100)
    }
}