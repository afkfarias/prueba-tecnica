package com.afarias.prueba_tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "EquipoRequestDto", description = "Dto para crear o editar un equipo.")
public class EquipoRequestDto {
  @Schema(description = "Nombre del equipo", example = "FC Barcelona")
  @NotBlank
  private String nombre;

  @Schema(description = "Liga", example = "La Liga")
  @NotBlank
  private String liga;

  @Schema(description = "Pais", example = "España")
  @NotBlank
  private String pais;
}
