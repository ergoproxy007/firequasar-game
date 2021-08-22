package com.dtorres.firequasar.command.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.KENOBI;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_KENOBI;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.SKYWALKER;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_SKYWALKER;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.SATO;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_SATO;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.createMessages;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.dtorres.firequasar.command.infrastructure.service.trileration.NonLinearTrilaterationService;
import com.dtorres.firequasar.common.CommonUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.concurrent.CompletionStage;

import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LocationServiceTest extends CommonUnitTest {

  @InjectMocks
  private LocationService locationService;

  @Mock
  private NonLinearTrilaterationService trilaterationService;

  @BeforeEach
  public void init() {
    when(trilaterationService.getLocation(any(), any())).thenCallRealMethod();
  }

  @Test
  public void calculatePositionByLocationTest() {
    //Arrange
    int count = 4;
    Double positionXPredicted = -58.315252587138595;
    Double positionYPredicted = -69.55141837312165;

    List<Spaceship> spaceships = new TestDataBuilderSpaceship()
                                      .add(KENOBI, DISTANCE_KENOBI, createMessages(count), new Position(-500.0, -200.0))
                                      .add(SKYWALKER, DISTANCE_SKYWALKER, createMessages(count), new Position(100.0, -100.0))
                                      .add(SATO, DISTANCE_SATO, createMessages(count), new Position(500.0, 100.0))
                                      .build();
    //Act
    CompletionStage<Position> positionPromise = locationService.calculatePositionAsync(spaceships);
    Position finalPosition = getResultPromise(positionPromise);
    //Assert
    assertEquals(positionXPredicted, finalPosition.getX(), "Trilateration result in X");
    assertEquals(positionYPredicted, finalPosition.getY(), "Trilateration result in Y");
  }
}
