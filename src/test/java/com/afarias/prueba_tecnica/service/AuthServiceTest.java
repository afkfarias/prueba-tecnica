package com.afarias.prueba_tecnica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.afarias.prueba_tecnica.dto.AuthResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

  @Mock private AuthenticationManager authenticationManager;

  @Mock private JwtService jwtService;

  @InjectMocks private AuthService authService;

  private String username;
  private String password;
  private String token;

  @BeforeEach
  void setup() {
    username = "test";
    password = "12345";
    token = "test-token";
  }

  @Test
  void loginConExito_tokenValido() {
    Authentication authentication = mock(Authentication.class);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(authentication);

    when(jwtService.generateToken(authentication)).thenReturn(token);

    AuthResponseDto authResponseDto = authService.login(username, password);

    assertNotNull(authResponseDto);
    assertEquals(token, authResponseDto.getToken());

    verify(authenticationManager, times(1))
        .authenticate(any(UsernamePasswordAuthenticationToken.class));
    verify(jwtService, times(1)).generateToken(any(Authentication.class));
  }
}
