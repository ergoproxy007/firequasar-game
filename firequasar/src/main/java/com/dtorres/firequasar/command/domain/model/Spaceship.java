package com.dtorres.firequasar.command.domain.model;

public class Spaceship {

  private String name;
  private double distance;
  private Position position;

  public Spaceship(){}

  public Spaceship(double distance, Position position) {
    this.distance = distance;
    this.position = position;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public double[] getPrimitivePosition() {
    return new double[] {position.getX(), position.getY()};
  }
}
