package com.dtorres.firequasar.command.domain.model;

import com.dtorres.firequasar.command.domain.entityerror.EntityError;
import com.dtorres.firequasar.shared.constants.ConstantsText;

import java.util.stream.Stream;

public class Spaceship {

  public static final String THE_MESSAGE_LIST_HAS_NO_INFORMATION = "The message list has no information";
  private static final String THE_NAME_IS_REQUIRED = "The name is required";
  private static final String THE_DISTANCE_IS_REQUIRED = "The distance is required";

  private String name;
  private Double distance;
  private String[] messages;
  private Position position;

  private EntityError entityError;

  public static Spaceship create(String name, Double distance, String[] messages) {
    Spaceship spaceship = new Spaceship(name, distance, messages);
    spaceship.getEntityError().hasErrorMessages();
    return spaceship;
  }

  private Spaceship(String name, Double distance, String[] messages) {
    this.entityError = new EntityError();

    this.setName(name);
    this.setDistance(distance);
    this.setMessages(messages);
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
    if(position == null) {
      position = new Position(-500.0, -100.0);
    }
    return new double[] {position.getX(), position.getY()};
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
    if(messages != null && messages.length >= 0) {
      Long numbersOfEmpties = Stream.of(messages).filter(message -> ConstantsText.EMPTY.equals(message)).count();
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
