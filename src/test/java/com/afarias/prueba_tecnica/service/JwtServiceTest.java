package com.afarias.prueba_tecnica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

  @Mock private JwtEncoder jwtEncoder;

  @InjectMocks private JwtService jwtService;

  private Authentication authentication;
  private String mockedToken = "test-jwt-token";

  @BeforeEach
  void setup() {
    authentication = mock(Authentication.class);
    when(authentication.getName()).thenReturn("testUser");
  }

  @Test
  void generateToken_happyPath() {
    Jwt jwt =
        new Jwt(
            mockedToken,
            Instant.now(),
            Instant.now().plus(60000, ChronoUnit.MILLIS),
            Map.of("alg", "HS256"),
            Map.of("subject", "testUser"));
    when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);

    String token = jwtService.generateToken(authentication);

    assertNotNull(token);
    assertEquals(mockedToken, token);

    verify(jwtEncoder, times(1)).encode(any(JwtEncoderParameters.class));
  }
}
