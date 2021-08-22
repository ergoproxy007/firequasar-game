package com.dtorres.firequasar.command.application.command;

import java.util.List;

public class SatelliteCommand {

  private String name;
  private double distance;
  private String[] message;

  public String getName() {
    return name;
  }

  public double getDistance() {
    return distance;
  }

  public String[] getMessage() {
    return message;
  }
}
