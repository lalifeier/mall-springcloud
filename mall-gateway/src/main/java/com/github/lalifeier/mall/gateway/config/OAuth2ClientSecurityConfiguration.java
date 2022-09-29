package com.github.lalifeier.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity(debug = true)
//public class OAuth2ClientSecurityConfiguration {
//
//    @Bean
//    SecurityFilterChain oauth2ClientSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeRequests()
//                .mvcMatchers(HttpMethod.GET,"/foo/bar").anonymous()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Client();
//        return httpSecurity.build();
//    }
//}
