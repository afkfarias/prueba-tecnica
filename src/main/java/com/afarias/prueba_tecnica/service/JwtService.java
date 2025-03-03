package com.afarias.prueba_tecnica.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${jwt.expiracion}")
  private long EXPIRACION_TOKEN;

  @Autowired private JwtEncoder jwtEncoder;

  public String generateToken(Authentication authentication) {
    JwsHeader jwtHeader = JwsHeader.with(MacAlgorithm.HS256).type("JWT").build();

    JwtClaimsSet claimsSet =
        JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(EXPIRACION_TOKEN, ChronoUnit.MILLIS))
            .subject(authentication.getName())
            .build();
    return jwtEncoder.encode(JwtEncoderParameters.from(jwtHeader, claimsSet)).getTokenValue();
  }
}
