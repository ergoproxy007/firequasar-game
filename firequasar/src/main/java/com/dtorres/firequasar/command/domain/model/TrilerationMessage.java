package com.dtorres.firequasar.command.domain.model;

public class TrilerationMessage {

  private Position position;
  private String message;

  public TrilerationMessage(){}

  public TrilerationMessage(Position position, String message) {
    this.position = position;
    this.message = message;
  }

  public Position getPosition() {
    return position;
  }

  public String getMessage() {
    return message;
  }

}
