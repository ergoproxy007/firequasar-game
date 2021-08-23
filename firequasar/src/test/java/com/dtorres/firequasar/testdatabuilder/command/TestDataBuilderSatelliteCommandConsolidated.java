package com.dtorres.firequasar.testdatabuilder.command;

import com.dtorres.firequasar.command.application.command.SatelliteCommand;
import com.dtorres.firequasar.command.application.command.SatelliteCommandConsolidated;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilderSatelliteCommandConsolidated {

  private SatelliteCommandConsolidated satelliteCommandConsolidated;

  private List<SatelliteCommand> satellites;

  public TestDataBuilderSatelliteCommandConsolidated() {
    this.satellites = new ArrayList<>();
  }

  public TestDataBuilderSatelliteCommandConsolidated add(String name, Double distance, String[] message) {
    this.satellites.add(new SatelliteCommand(name, distance, message));
    return this;
  }

  public SatelliteCommandConsolidated build() {
    return new SatelliteCommandConsolidated(satellites);
  }
}
