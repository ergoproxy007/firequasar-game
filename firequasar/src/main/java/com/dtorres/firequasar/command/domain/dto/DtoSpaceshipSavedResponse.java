package com.dtorres.firequasar.command.domain.dto;

public final class DtoSpaceshipSavedResponse {

  private String name;
  private String result;

  public DtoSpaceshipSavedResponse() {}

  public DtoSpaceshipSavedResponse(String name, String result) {
    this.name = name;
    this.result = result;
  }
  public String getName() {
    return name;
  }
  public String getResult() {
    return result;
  }
}
