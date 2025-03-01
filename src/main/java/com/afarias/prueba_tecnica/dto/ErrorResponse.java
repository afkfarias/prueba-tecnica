package com.afarias.prueba_tecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ErrorResponse {
  String mensaje;
  int codigo;
}
