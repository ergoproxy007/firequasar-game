package com.dtorres.firequasar.command.application.command;

import java.io.Serializable;
import java.util.List;

public class SatelliteCommandConsolidated implements Serializable {

  private List<SatelliteCommand> satellites;

  public List<SatelliteCommand> getSatellites() {
    return satellites;
  }

  public void setSatellites(List<SatelliteCommand> satellites) {
    this.satellites = satellites;
  }
}
