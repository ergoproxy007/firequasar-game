package com.dtorres.firequasar.query.domain.model;

public final class DtoSpaceship {

  private String name;
  private Double distance;
  private String[] messages;
  private DtoPosition position;

  public DtoSpaceship(){}

  public DtoSpaceship(String name, Double distance, String[] messages, Double x, Double y) {
    this.name = name;
    this.distance = distance;
    this.messages = messages;
    this.position = new DtoPosition(x, y);
  }

  public String getName() {
    return name;
  }
  public Double getDistance() {
    return distance;
  }
  public String[] getMessages() {
    return messages;
  }
  public DtoPosition getPosition() {
    return position;
  }
}
