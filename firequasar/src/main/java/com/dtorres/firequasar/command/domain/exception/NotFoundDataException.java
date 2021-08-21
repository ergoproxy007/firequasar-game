package com.dtorres.firequasar.command.domain.exception;

public class NotFoundDataException extends DomainException {

  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_MESSAGE = "No data found in request";

  public NotFoundDataException() {
    super(DEFAULT_MESSAGE);
  }

  public NotFoundDataException(String message) {
    super(message);
  }

}