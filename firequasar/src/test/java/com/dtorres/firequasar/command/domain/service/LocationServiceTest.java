package com.dtorres.firequasar.command.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.KENOBI;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_KENOBI;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.SKYWALKER;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_SKYWALKER;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.SATO;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_SATO;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.createMessages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LocationServiceTest {

  @InjectMocks
  private LocationService locationService;

  @Test
  public void calculatePositionByLocationTest() {
    int count = 4;
    Double positionXPredicted = -58.315252587138595;
    Double positionYPredicted = -69.55141837312165;

    List<Spaceship> spaceships = new TestDataBuilderSpaceship()
                                      .add(KENOBI, DISTANCE_KENOBI, createMessages(count), new Position(-500.0, -200.0))
                                      .add(SKYWALKER, DISTANCE_SKYWALKER, createMessages(count), new Position(100.0, -100.0))
                                      .add(SATO, DISTANCE_SATO, createMessages(count), new Position(500.0, 100.0))
                                      .build();

    Position finalPosition = locationService.calculatePositionByLocation(spaceships);

    assertEquals(positionXPredicted, finalPosition.getX(), "Trilateration result in X");
    assertEquals(positionYPredicted, finalPosition.getY(), "Trilateration result in Y");
  }
}
