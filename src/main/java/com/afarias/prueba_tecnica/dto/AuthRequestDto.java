package com.afarias.prueba_tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
  @Schema(description = "Nombre de usuario", example = "test")
  private String username;

  @Schema(description = "Contraseña", example = "12345")
  private String password;
}
