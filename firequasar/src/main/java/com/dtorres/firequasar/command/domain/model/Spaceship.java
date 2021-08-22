package com.dtorres.firequasar.command.domain.model;

import static com.dtorres.firequasar.shared.constants.ConstantsNumber.ZERO;
import static com.dtorres.firequasar.shared.constants.ConstantsText.EMPTY;

import com.dtorres.firequasar.command.domain.entityerror.EntityError;

import java.util.stream.Stream;

public class Spaceship {

  public static final String THE_MESSAGE_LIST_HAS_NO_INFORMATION = "The message list has no information";
  private static final String THE_NAME_IS_REQUIRED = "The name is required";
  private static final String THE_DISTANCE_IS_REQUIRED = "The distance is required";

  private String name;
  private Double distance;
  private String[] messages;
  private Position position;

  private final EntityError entityError;

  public static Spaceship create(String name, Double distance, String[] messages) {
    Spaceship spaceship = new Spaceship(name, distance, messages);
    spaceship.getEntityError().hasErrorMessages();
    return spaceship;
  }

  public static Spaceship create(String name, Double x, Double y) {
    return new Spaceship(name, x, y);
  }

  private Spaceship(String name, Double distance, String[] messages) {
    this.entityError = new EntityError();

    this.setName(name);
    this.setDistance(distance);
    this.setMessages(messages);
  }

  private Spaceship(String name, Double x, Double y) {
    this.entityError = new EntityError();

    this.setName(name);
    this.setPosition(new Position(x, y));
  }

  public String getName() {
    return name;
  }

  public Double getDistance() {
    return distance;
  }

  public String[] getMessages() {
    return messages;
  }

  public Position getPosition() {
    return position;
  }

  public EntityError getEntityError() {
    return entityError;
  }

  public double[] getPrimitivePosition() {
    if(position != null) {
      return new double[] {position.getX(), position.getY()};
    }
    return new double[0];
  }

  private void setName(String name) {
    this.entityError.validateIsNullOrEmpty(name, THE_NAME_IS_REQUIRED);
    this.name = name;
  }

  private void setDistance(Double distance) {
    this.entityError.validateIsNullOrEmpty(distance, THE_DISTANCE_IS_REQUIRED);
    this.distance = distance;
  }

  private void setMessages(String[] messages) {
    if(messages != null && messages.length > ZERO) {
      Long numbersOfEmpties = Stream.of(messages).filter(message -> EMPTY.equals(message)).count();
      if(numbersOfEmpties.intValue() == messages.length) {
        this.entityError.add(THE_MESSAGE_LIST_HAS_NO_INFORMATION);
      }
    } else {
      this.entityError.add("The messages list is empty");
    }
    this.messages = messages;
  }

  public void setPosition(Position position) {
    this.position = position;
  }
}
