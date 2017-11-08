package  com.ly.immortalblog.config

import com.ly.immortalblog.config.filter.JWTAuthenticationFilter
import com.ly.immortalblog.config.filter.JWTLoginFilter
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(val immortalUserDetailsServiceImpl: UserDetailsService) : WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(immortalUserDetailsServiceImpl)
    }

    override fun configure(http: HttpSecurity?) {
        val jwtLoginFilter = JWTLoginFilter()
        jwtLoginFilter.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/api/login", "POST"))
        jwtLoginFilter.setAuthenticationManager(authenticationManager())

        http!!
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .addFilter(jwtLoginFilter)
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
    }
}