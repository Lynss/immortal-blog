package  com.ly.immortalblog.config

import com.ly.immortalblog.domain.model.BasUserExample
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = arrayOf("com.ly.immortalblog.domain.model"))
class MybatisConfig{

}