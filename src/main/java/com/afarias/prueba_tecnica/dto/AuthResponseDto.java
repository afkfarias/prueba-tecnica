package com.afarias.prueba_tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthResponseDto {

  @Schema(description = "Token JWT", example = "eyJhbGciOiJIUz")
  String token;
}
