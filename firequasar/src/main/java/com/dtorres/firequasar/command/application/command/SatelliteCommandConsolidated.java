package com.dtorres.firequasar.command.application.command;

import java.io.Serializable;
import java.util.List;

public class SatelliteCommandConsolidated implements Serializable {

  private List<SatelliteCommand> satellites;

  public SatelliteCommandConsolidated(){}

  public SatelliteCommandConsolidated(List<SatelliteCommand> satellites) {
    this.satellites = satellites;
  }

  public List<SatelliteCommand> getSatellites() {
    return satellites;
  }

}
