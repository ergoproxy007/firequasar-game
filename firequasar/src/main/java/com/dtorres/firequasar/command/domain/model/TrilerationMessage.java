package com.dtorres.firequasar.command.domain.model;

public class TrilerationMessage {

  private Position position;
  private String message;

  public TrilerationMessage() {
    this.message = "SOS";
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
