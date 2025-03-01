package com.afarias.prueba_tecnica.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EquipoResponseDto {

  Long id;
  String nombre;
  String liga;
  String pais;
}
