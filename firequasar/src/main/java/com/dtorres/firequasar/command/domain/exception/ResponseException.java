package com.dtorres.firequasar.command.domain.exception;

public abstract class ResponseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ResponseException(String message) {
    super(message);
  }

}
