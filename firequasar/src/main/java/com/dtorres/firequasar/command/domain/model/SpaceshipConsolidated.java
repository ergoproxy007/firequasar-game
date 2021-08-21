package com.dtorres.firequasar.command.domain.model;

import java.io.Serializable;
import java.util.List;

public class SpaceshipConsolidated implements Serializable {

  private List<Spaceship> spaceships;

  public List<Spaceship> getSpaceships() {
    return spaceships;
  }

  public void setSpaceships(List<Spaceship> spaceships) {
    this.spaceships = spaceships;
  }
}
