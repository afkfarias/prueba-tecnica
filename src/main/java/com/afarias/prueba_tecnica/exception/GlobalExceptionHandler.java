package com.afarias.prueba_tecnica.exception;

import com.afarias.prueba_tecnica.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final String ERROR_INTERNO_SERVIDOR = "Error interno del servidor";
  private final String ERROR_INTERNO_BD = "Error en la base de datos";

  private final String SOLICITUD_NO_VALIDA = "La solicitud es inválida";

  // Manejo de excepciones para entidades no encontradas
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  // Manejo de excepcion bad request en caso de necesitar lanzar este tipo de excepciones
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  // Manejo de excepciones genérícas
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse(ERROR_INTERNO_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR.value()));
  }

  // Manejo de excepciones de base de datos
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDatabaseException(DataIntegrityViolationException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(ERROR_INTERNO_BD, HttpStatus.BAD_REQUEST.value()));
  }

  // Manejo de excepciones badRequest relacionadas a validaciones en requestDto y request body vacío
  @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
  public ResponseEntity<ErrorResponse> handleValidationRequestException(Exception ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(SOLICITUD_NO_VALIDA, HttpStatus.BAD_REQUEST.value()));
  }

  // Manejo de excepciones badCredentials en login
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<Void> handleBadCredentialsException(BadCredentialsException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}
