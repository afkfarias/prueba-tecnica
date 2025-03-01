package com.afarias.prueba_tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "EquipoResponseDto", description = "Dto con información de equipo.")
public class EquipoResponseDto {

  @Schema(description = "Identificador del equipo", example = "5162")
  Long id;

  @Schema(description = "Nombre del equipo", example = "Bayer Munich")
  String nombre;

  @Schema(description = "Liga", example = "Bundesliga")
  String liga;

  @Schema(description = "Pais", example = "Alemania")
  String pais;
}
