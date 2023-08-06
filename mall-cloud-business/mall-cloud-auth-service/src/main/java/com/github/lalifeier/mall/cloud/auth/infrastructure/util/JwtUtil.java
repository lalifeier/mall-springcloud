package com.github.lalifeier.mall.cloud.auth.infrastructure.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

public class JwtUtil {

  private static final String BEARER = "Bearer ";

  private static final String ISSUER = "your_issuer";
  private static final String AUDIENCE = "your_audience";
  private static final String SUBJECT = "your_subject";
  private static final long EXPIRATION_TIME = 3600000; // 1 hour

  private static RSAPrivateKey privateKey;
  private static RSAPublicKey publicKey;

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

  public static String generateToken() {
    try {
      JWSSigner signer = new RSASSASigner(privateKey);

      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .issuer(ISSUER)
        .subject(SUBJECT)
        .audience(AUDIENCE)
        .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .build();

      SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).keyID("1").build(), claimsSet);

      signedJWT.sign(signer);

      return signedJWT.serialize();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static boolean verifyToken(String token) {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);
      JWSVerifier verifier = new RSASSAVerifier(publicKey);

      return signedJWT.verify(verifier);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean isTokenExpired(String token) {
    Date expiration = extractExpiration(token);
    return expiration != null && expiration.before(Date.from(Instant.now()));
  }

  public static String extractUsername(String token) {
    return extractClaim(token, jwsObject -> jwsObject.getPayload().toJSONObject().get("sub").toString());
  }

  public static Date extractExpiration(String token) {
    return extractClaim(token, jwsObject -> (Date) jwsObject.getPayload().toJSONObject().get("exp"));
  }

  private static <T> T extractClaim(String token, Function<JWSObject, T> claimsResolver) {
    try {
      JWSObject jwsObject = JWSObject.parse(token);
      return claimsResolver.apply(jwsObject);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }
}

