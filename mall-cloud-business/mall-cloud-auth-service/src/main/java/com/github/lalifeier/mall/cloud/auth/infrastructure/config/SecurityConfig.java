package com.github.lalifeier.mall.cloud.auth.infrastructure.config;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;
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
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests((authorize) -> authorize
        .anyRequest().authenticated()
      )
      // Form login handles the redirect to the login page from the
      // authorization server filter chain
      .formLogin(withDefaults());

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

  //  @Bean
//  public AuthenticationProvider smsAuthenticationProvider(){
//    AuthenticationProvider authenticationProvider = new SmsAuthenticationProvider();
//    return authenticationProvider;
//  }
//
//  @Bean
//  public AuthenticationProvider passwordAuthenticationProvider() {
//    AuthenticationProvider authenticationProvider = new PasswordAuthenticationProvider();
//    authenticationProvider.setUserDetailsService(userDetailsService());
//    authenticationProvider.setPasswordEncoder(passwordEncoder());
//    return authenticationProvider;
//  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

//    authenticationManagerBuilder.authenticationProvider(passwordAuthenticationProvider())
//      .authenticationProvider(oauth2RefreshTokenAuthenticationProvider());
//    return authenticationManagerBuilder.getOrBuild();

    return config.getAuthenticationManager();
  }
}
