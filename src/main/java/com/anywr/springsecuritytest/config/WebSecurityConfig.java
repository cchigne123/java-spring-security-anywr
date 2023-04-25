package com.anywr.springsecuritytest.config;

import com.anywr.springsecuritytest.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CustomUserDetailService userDetailsService;
    private final AccountAuthenticationProvider accountAuthenticationProvider;
    private final AuthenticationManagerBuilder auth;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/user/**").permitAll()
                .and().csrf().disable();
                //.authenticationManager(buildAuthenticationManagerBuilder(auth));
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(accountAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

}
