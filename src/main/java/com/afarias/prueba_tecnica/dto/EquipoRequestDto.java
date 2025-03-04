package com.afarias.prueba_tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
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
