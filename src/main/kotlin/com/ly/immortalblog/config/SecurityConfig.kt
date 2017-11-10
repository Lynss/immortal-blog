package  com.ly.immortalblog.config

import com.ly.immortalblog.config.filter.JwtAuthenticationTokenFilter
import com.ly.immortalblog.domain.constant.Urls
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(val userDetailsService: UserDetailsService,
                     val jwtAuthenticationTokenFilter:JwtAuthenticationTokenFilter) : WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService)
    }

    override fun configure(http: HttpSecurity?) {
        http!!
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(Urls.AUTH_API+"/**").permitAll()
                .anyRequest().authenticated()

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

        http.headers().cacheControl()

    }

}