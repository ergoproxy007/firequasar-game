package com.dtorres.firequasar.command.application.command;

import java.util.List;

public class SatelliteCommand {

  private String name;
  private Double distance;
  private String[] message;

  public SatelliteCommand(){}

  public SatelliteCommand(String name, Double distance, String[] message) {
    this.name = name;
    this.distance = distance;
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public Double getDistance() {
    return distance;
  }

  public String[] getMessage() {
    return message;
  }
}
