package com.github.lalifeier.mall.cloud.auth.infrastructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity.UserPrincipal;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {
  private static RSAPrivateKey privateKey;
  private static RSAPublicKey publicKey;

  private static final String JWT_PAYLOAD_USER_KEY = "user";

  static {
    generateKeyPair();
  }

  private static void generateKeyPair() {
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      privateKey = (RSAPrivateKey) keyPair.getPrivate();
      publicKey = (RSAPublicKey) keyPair.getPublic();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String generateToken(UserPrincipal userPrincipal, int expire) {
    String token = null;
    try {
      JWSSigner signer = new RSASSASigner(privateKey);

      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().claim(JWT_PAYLOAD_USER_KEY, userPrincipal)
          .expirationTime(new Date(System.currentTimeMillis() + expire)).build();

      SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
      signedJWT.sign(signer);
      token = signedJWT.serialize();
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    return token;
  }

  public static JWTClaimsSet parserToken(String token) {
    JWTClaimsSet claims = null;
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);
      JWSVerifier verifier = new RSASSAVerifier(publicKey);

      if (signedJWT.verify(verifier)) {
        claims = signedJWT.getJWTClaimsSet();
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return claims;
  }

  public static boolean isTokenExpired(String token) {
    JWTClaimsSet claims = parserToken(token);
    return claims != null && claims.getExpirationTime().after(new Date());
  }

  public static UserPrincipal getUserInfo(String token) {
    UserPrincipal user = null;
    try {
      JWTClaimsSet claims = parserToken(token);
      if (claims != null && claims.getExpirationTime().after(new Date())) {
        Object jsonObject = claims.getClaim(JWT_PAYLOAD_USER_KEY);
        user = new ObjectMapper().readValue(jsonObject.toString(), UserPrincipal.class);
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return user;
  }
}
