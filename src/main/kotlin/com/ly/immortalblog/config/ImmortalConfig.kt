package  com.ly.immortalblog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@Configuration
@Import(DbConfig::class,MybatisConfig::class,SecurityConfig::class)
@EnableScheduling
class ImmortalConfig {

    /**
     *spring-task多线程配置
     */
    fun configureTasks() {
        taskRegistrar().setScheduler(taskExecutor())
    }

    @Bean
    fun taskRegistrar():ScheduledTaskRegistrar{
        return ScheduledTaskRegistrar()
    }

    @Bean
    fun taskExecutor(): Executor {
        return Executors.newScheduledThreadPool(100)
    }
}