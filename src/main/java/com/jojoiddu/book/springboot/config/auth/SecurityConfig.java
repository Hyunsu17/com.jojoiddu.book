package com.jojoiddu.book.springboot.config.auth;

import com.jojoiddu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화 한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .csrf().disable()//csrf란 cross Script Resourse Forgery
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests()
                .antMatchers("/","/css**","images/**","/js/**","/h2-console/**","/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole((Role.USER.name()))
                .anyRequest().authenticated()
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService);

    }
}
