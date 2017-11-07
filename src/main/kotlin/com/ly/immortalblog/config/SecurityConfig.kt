package  com.ly.immortalblog.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
@EnableWebSecurity
class SecurityConfig(val immortalUserDetailsServiceImpl: UserDetailsService): WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(immortalUserDetailsServiceImpl)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .antMatchers("/api/**").authenticated()
//                .antMatchers(HttpMethod.POST).access("hasRole('ROLE_GUEST') and hasIpAddress('10.110.5.5')")
                .anyRequest().permitAll()
//                .and()
//                .requiresChannel()
//                .antMatchers("/api/**").requiresSecure()
                .and()
                .httpBasic()
                .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("rememberMe")
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf().disable()
    }
}