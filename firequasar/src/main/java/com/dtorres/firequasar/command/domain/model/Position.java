package com.dtorres.firequasar.command.domain.model;

import com.dtorres.firequasar.command.domain.entityerror.EntityError;

public class Position {

  private static final String THE_COORDENATE_X_IS_REQUIRED = "The coordenate X is required";
  private static final String THE_COORDENATE_Y_IS_REQUIRED = "The coordenate X is required";

  private Double x;
  private Double y;

  private EntityError entityError;

  public Position(Double x, Double y) {
    this.entityError = new EntityError();

    this.setX(x);
    this.setY(y);

    this.entityError.hasErrorMessages();
  }

  public Double getX() {
    return x;
  }

  public Double getY() {
    return y;
  }

  private void setX(Double x) {
    this.entityError.validateIsNullOrEmpty(x, THE_COORDENATE_X_IS_REQUIRED);
    this.x = x;
  }

  private void setY(Double y) {
    this.entityError.validateIsNullOrEmpty(y, THE_COORDENATE_Y_IS_REQUIRED);
    this.y = y;
  }
}
