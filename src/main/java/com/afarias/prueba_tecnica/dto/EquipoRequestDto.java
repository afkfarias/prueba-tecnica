package com.afarias.prueba_tecnica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipoRequestDto {
  @NotBlank private String nombre;
  @NotBlank private String liga;
  @NotBlank private String pais;
}
