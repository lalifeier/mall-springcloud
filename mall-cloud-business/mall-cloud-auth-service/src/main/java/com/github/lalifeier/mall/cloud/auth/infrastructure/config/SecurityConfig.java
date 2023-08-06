package com.github.lalifeier.mall.cloud.auth.infrastructure.config;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.UserService;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.config.PasswordAuthenticationSecurityConfig;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.handler.RestAuthenticationFailureHandler;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.handler.RestAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig {

  private final UserService userService;

//  private final DataSource dataSource;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/webjars/**", "/assets/**", "/actuator/health", "/h2-console/**");
  }

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


    http
      .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
      .formLogin(withDefaults())
//      .formLogin(formLogin -> {
//        formLogin.loginPage("/login");
////        formLogin.successHandler(authenticationSuccessHandler());
////        formLogin.failureHandler(authenticationFailureHandler());
//      })
      .csrf(csrf -> csrf.disable());
//      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//      .apply(passwordAuthenticationSecurityConfig());


//    http.addFilterBefore()

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
//    return new JdbcUserDetailsManager(dataSource);
    return username -> userService.loadUserByUsername(username);
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  @SneakyThrows
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordAuthenticationSecurityConfig passwordAuthenticationSecurityConfig() {
    PasswordAuthenticationSecurityConfig passwordAuthenticationSecurityConfig = new PasswordAuthenticationSecurityConfig();
    passwordAuthenticationSecurityConfig.setUserService(userService);
    passwordAuthenticationSecurityConfig.setAuthenticationSuccessHandler(authenticationSuccessHandler());
    passwordAuthenticationSecurityConfig.setAuthenticationFailureHandler(authenticationFailureHandler());
    return passwordAuthenticationSecurityConfig;
  }

  @Bean
  RestAuthenticationSuccessHandler authenticationSuccessHandler() {
    return new RestAuthenticationSuccessHandler();
  }

  @Bean
  RestAuthenticationFailureHandler authenticationFailureHandler() {
    return new RestAuthenticationFailureHandler();
  }
}
