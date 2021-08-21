package com.dtorres.firequasar.command.domain.exception;

public class TopSecretException extends DomainException {

  private static final long serialVersionUID = 1L;

  public TopSecretException(String message) {
    super(message);
  }
}
