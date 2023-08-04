package com.github.lalifeier.mall.cloud.auth.infrastructure.util;

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
}

