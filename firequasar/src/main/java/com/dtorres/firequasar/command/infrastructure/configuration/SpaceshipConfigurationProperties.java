package com.dtorres.firequasar.command.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties("app")
public class SpaceshipConfigurationProperties {

  private List<SpaceshipProperties> spaceships = new ArrayList<>();

  public List<SpaceshipProperties> getSpaceships() {
    return spaceships;
  }
  public void setSpaceships(List<SpaceshipProperties> spaceships) {
    this.spaceships = spaceships;
  }

  public static class SpaceshipProperties {
    private String name;
    private Double coordinateX;
    private Double coordinateY;

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public Double getCoordinateX() {
      return coordinateX;
    }
    public void setCoordinateX(Double coordinateX) {
      this.coordinateX = coordinateX;
    }
    public Double getCoordinateY() {
      return coordinateY;
    }
    public void setCoordinateY(Double coordinateY) {
      this.coordinateY = coordinateY;
    }
  }
}
