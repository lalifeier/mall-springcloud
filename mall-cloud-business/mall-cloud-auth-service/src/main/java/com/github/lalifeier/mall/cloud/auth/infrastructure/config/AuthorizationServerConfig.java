package com.github.lalifeier.mall.cloud.auth.infrastructure.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
    throws Exception {
    // 对授权服务器进行一些默认的配置
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

    OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = http
      .getConfigurer(OAuth2AuthorizationServerConfigurer.class);

//    SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
//    SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
//    http.authenticationProvider(smsAuthenticationProvider)
//      .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//

//    authorizationServerConfigurer.authorizationServerMetadataEndpoint(metadata -> metadata.authorizationServerMetadataCustomizer(customizer -> customizer.grantType(SecurityConstants.GRANT_TYPE_SMS_CODE)))
//      .tokenEndpoint(tokenEndpoint -> tokenEndpoint
//        .accessTokenRequestConverter(new SmsAuthenticationToken())
//        .authenticationProvider(new SmsAuthenticationProvider()));

    //    支持OpenID Connect 1.0, scope如果有openid的话,需要配置这个
    authorizationServerConfigurer.oidc(Customizer.withDefaults());

    http.exceptionHandling((exceptions) -> exceptions
      .defaultAuthenticationEntryPointFor(
        new LoginUrlAuthenticationEntryPoint("/login"),
        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
      )
    );

    return http.build();
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcRegisteredClientRepository(jdbcTemplate);
  }

  @Bean
  public OAuth2AuthorizationService authorizationService(
    JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
  }

  @Bean
  public OAuth2AuthorizationConsentService authorizationConsentService(
    JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
    return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
  }

  /**
   * 自定义jwt，将权限信息放至jwt中
   *
   * @return
   */
//  @Bean
//  public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer() {
//    return context -> {
//      if (context.getPrincipal().getPrincipal() instanceof UserDetails user) {
//        // 获取申请的scopes
//        Set<String> scopes = context.getAuthorizedScopes();
//        // 获取用户的权限
//        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//        // 提取权限并转为字符串
//        Set<String> authoritySet = Optional.ofNullable(authorities).orElse(Collections.emptyList()).stream()
//          // 获取权限字符串
//          .map(GrantedAuthority::getAuthority)
//          // 去重
//          .collect(Collectors.toSet());
//
//        // 合并scope与用户信息
//        authoritySet.addAll(scopes);
//
//        JwtClaimsSet.Builder claims = context.getClaims();
//        // 将权限信息放入jwt的claims中（也可以生成一个以指定字符分割的字符串放入）
//        claims.claim("authorities", authoritySet);
//      }
//    };
//  }
  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    KeyPair keyPair = generateRsaKey();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    RSAKey rsaKey =
      new RSAKey.Builder(publicKey)
        .privateKey(privateKey)
        .keyID(UUID.randomUUID().toString())
        .build();
    JWKSet jwkSet = new JWKSet(rsaKey);
    return new ImmutableJWKSet<>(jwkSet);
  }

  private static KeyPair generateRsaKey() {
    KeyPair keyPair;
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      keyPair = keyPairGenerator.generateKeyPair();
    } catch (Exception ex) {
      throw new IllegalStateException(ex);
    }
    return keyPair;
  }

  @Bean
  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }


  @Bean
  public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
  }

  // @Bean
  // public EmbeddedDatabase embeddedDatabase() {
  //    return new EmbeddedDatabaseBuilder()
  //            .generateUniqueName(true)
  //            .setType(EmbeddedDatabaseType.H2)
  //            .setScriptEncoding("UTF-8")
  //
  // .addScript("org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql")
  //
  // .addScript("org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql")
  //
  // .addScript("org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql")
  //            .build();
  // }

}
