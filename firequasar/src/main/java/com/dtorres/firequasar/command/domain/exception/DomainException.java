package com.dtorres.firequasar.command.domain.exception;

public abstract class DomainException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DomainException(String message) {
    super(message);
  }

}
