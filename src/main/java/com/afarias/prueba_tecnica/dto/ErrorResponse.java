package com.afarias.prueba_tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Respuesta en caso de error.")
public class ErrorResponse {
  @Schema(description = "Codigo de error", example = "404")
  String mensaje;

  @Schema(description = "Mensaje descriptivo del error", example = "Equipo no encontrado")
  int codigo;
}
