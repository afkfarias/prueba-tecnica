package com.afarias.prueba_tecnica.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException() {
    super("La solicitud es inválida");
  }
}
