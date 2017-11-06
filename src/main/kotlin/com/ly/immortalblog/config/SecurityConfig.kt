package  com.ly.immortalblog.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    /**
     * use jdbc
     * the most easy config works based on the appoint
     */
    @Autowired
    lateinit var primaryDataSource: DataSource
    @DependsOn("primaryDataSource")

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.jdbcAuthentication().dataSource(primaryDataSource)
    }
}