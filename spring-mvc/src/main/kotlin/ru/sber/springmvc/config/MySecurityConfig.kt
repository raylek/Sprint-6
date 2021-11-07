package ru.sber.springmvc.config


import org.springframework.security.config.web.servlet.invoke

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User

@EnableWebSecurity
open class MySecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login").not()
                .fullyAuthenticated()
                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers("/app/**").authenticated()
                .antMatchers("/",).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/app/add")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        val userBuilder = User.withDefaultPasswordEncoder()
        auth.inMemoryAuthentication()
            .withUser(userBuilder.username("admin").password("admin").roles("ADMIN"))
            .withUser(userBuilder.username("api").password("api").roles("API"))
            .withUser(userBuilder.username("ivan").password("ivan").roles("DIFFERENT"))
    }
}
