package com.github.lalifeier.mall.cloud.monitor.config;

import com.alibaba.nacos.common.utils.CollectionUtils;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String adminContextPath;
    private final List<String> patterns;

    public SecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();

        this.patterns =
                Arrays.asList(
                        this.adminContextPath + "/assets/**",
                        this.adminContextPath + "/login",
                        "/actuator/**",
                        "/actuator",
                        "/instances",
                        "/instances/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        return httpSecurity
                .headers()
                .frameOptions(frameOptionsCustomizer -> frameOptionsCustomizer.disable())
                .and()
                .authorizeRequests(
                        authorizeHttpRequestsCustomizer -> {
                            authorizeHttpRequestsCustomizer
                                    .requestMatchers(toRequestMatchers(this.patterns))
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated();
                        })
                .formLogin(
                        formLoginCustomizer ->
                                formLoginCustomizer
                                        .loginPage(adminContextPath + "/login")
                                        .successHandler(successHandler))
                .logout(
                        logoutCustomizer ->
                                logoutCustomizer.logoutUrl(adminContextPath + "/logout"))
                .httpBasic()
                .and()
                .csrf(csrf -> csrf.disable())
                .build();
    }

    public RequestMatcher[] toRequestMatchers(List<String> paths) {
        if (CollectionUtils.isNotEmpty(paths)) {
            List<AntPathRequestMatcher> matchers =
                    paths.stream().map(AntPathRequestMatcher::new).toList();
            RequestMatcher[] result = new RequestMatcher[matchers.size()];
            return matchers.toArray(result);
        } else {
            return new RequestMatcher[] {};
        }
    }
}
