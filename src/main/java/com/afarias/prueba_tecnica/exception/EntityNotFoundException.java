package com.afarias.prueba_tecnica.exception;

public class EntityNotFoundException extends RuntimeException {
  public <T> EntityNotFoundException(Class<T> entityClass) {
    super(entityClass.getSimpleName() + " no encontrado");
  }
}
