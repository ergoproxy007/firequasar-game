package com.dtorres.firequasar.shared.advice.dto;

public class DtoResponseEntityException {

  private String error;

  public DtoResponseEntityException(String error) {
    this.error = error;
  }
  public String getError() {
    return error;
  }
  public void setError(String message) {
    this.error = error;
  }
}
