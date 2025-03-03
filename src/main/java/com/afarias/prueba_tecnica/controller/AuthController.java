package com.afarias.prueba_tecnica.controller;

import com.afarias.prueba_tecnica.dto.AuthRequestDto;
import com.afarias.prueba_tecnica.dto.AuthResponseDto;
import com.afarias.prueba_tecnica.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints para autenticación.")
public class AuthController {

  private final AuthService authService;

  @Operation(
      summary = "Autenticarse en la Api",
      description = "Retorna un token de tipo JWT válido.")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "Token generado.")})
  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {

    return ResponseEntity.ok(
        authService.login(authRequestDto.getUsername(), authRequestDto.getPassword()));
  }
}
