package com.dtorres.firequasar.commons.domain.exceptions;

public abstract class ResponseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ResponseException(String message) {
    super(message);
  }

}
