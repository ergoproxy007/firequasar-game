package com.dtorres.firequasar.commons.domain.exceptions;

public class NotFoundDataException extends DomainException {

  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_MESSAGE = "No data found in request or query";

  public NotFoundDataException() {
    super(DEFAULT_MESSAGE);
  }

  public NotFoundDataException(String message) {
    super(message);
  }

}
