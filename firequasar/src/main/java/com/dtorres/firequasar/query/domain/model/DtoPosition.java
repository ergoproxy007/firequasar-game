package com.dtorres.firequasar.query.domain.model;

public final class DtoPosition {

  private Double x;
  private Double y;

  public DtoPosition(){}

  public DtoPosition(Double x, Double y) {
    this.x = x;
    this.y = y;
  }

  public Double getX() {
    return x;
  }
  public Double getY() {
    return y;
  }
}
