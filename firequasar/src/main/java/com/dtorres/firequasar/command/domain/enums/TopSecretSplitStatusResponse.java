package com.dtorres.firequasar.command.domain.enums;

public enum TopSecretSplitStatusResponse {

  SAVED("SAVED");

  private String status;

  TopSecretSplitStatusResponse(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return this.status;
  }
}
