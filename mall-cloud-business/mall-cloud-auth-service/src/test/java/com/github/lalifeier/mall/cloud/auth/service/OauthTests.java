package com.github.lalifeier.mall.cloud.auth.service;

import java.time.Duration;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

@Slf4j
@SpringBootTest
public class OauthTests {

  // @Autowired private UserDetailsManager userDetailsManager;

  @Autowired
  private RegisteredClientRepository registeredClientRepository;

  @Test
  void testSaveUser() {
    UserDetails userDetails =
        User.builder().passwordEncoder(s -> new BCryptPasswordEncoder().encode(s)).username("user")
            .password("123456").roles("ADMIN").build();
    // userDetailsManager.createUser(userDetails);
  }

  @Test
  void testSaveClient() {
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("gateway_client").clientSecret(new BCryptPasswordEncoder().encode("123456"))
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        // .authorizationGrantType(AuthorizationGrantType.PASSWORD)
        // .authorizationGrantType(AuthorizationGrantType.IMPLICIT)
        .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
        .redirectUri("http://127.0.0.1:8080/authorized").scope(OidcScopes.OPENID)
        .scope("message.read").redirectUri("https://www.baidu.com").scope(OidcScopes.OPENID)
        .scope(OidcScopes.PROFILE).scope("user.userInfo").scope("all")
        .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(12))
            .refreshTokenTimeToLive(Duration.ofDays(30)).reuseRefreshTokens(true).build())
        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build()).build();
    // registeredClientRepository.save(registeredClient);
  }
}
