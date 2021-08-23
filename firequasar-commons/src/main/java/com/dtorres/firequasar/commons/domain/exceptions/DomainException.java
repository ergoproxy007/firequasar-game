package com.dtorres.firequasar.commons.domain.exceptions;

public class DomainException extends ResponseEntityException {

  public DomainException(String message) {
    super(message);
  }

}
