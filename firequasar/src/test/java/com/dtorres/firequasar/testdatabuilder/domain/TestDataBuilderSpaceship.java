package com.dtorres.firequasar.testdatabuilder.domain;

import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDataBuilderSpaceship {

  public static final String KENOBI = "Kenobi";
  public static final Double DISTANCE_KENOBI = 100.0;
  public static final String SKYWALKER = "Skywalker";
  public static final Double DISTANCE_SKYWALKER = 115.5;
  public static final String SATO = "Sato";
  public static final Double DISTANCE_SATO = 142.7;

  private final List<Spaceship> spaceships;

  public TestDataBuilderSpaceship() {
    this.spaceships = new ArrayList<>();
  }

  public static String[] createMessages(int count) {
    String[] messages = new String[count];
    for(int i = 0; i < count; i++) {
      messages[i] = UUID.randomUUID().toString().replace("-", "");
    }
    return messages;
  }

  public TestDataBuilderSpaceship add(String name, Double distance, String[] messages, Position position) {
    Spaceship spaceship = Spaceship.create(name, distance, messages);
    spaceship.setPosition(position);
    this.spaceships.add(spaceship);
    return this;
  }

  public List<Spaceship> build() {
    return spaceships;
  }

}
