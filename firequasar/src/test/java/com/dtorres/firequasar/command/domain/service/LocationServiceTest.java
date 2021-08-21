package com.dtorres.firequasar.command.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LocationServiceTest {

  @InjectMocks
  private LocationService locationService;

  @Test
  public void getPositionTest() {
    Double positionXPredicted = -58.315252587138595;
    Double positionYPredicted = -69.55141837312165;

    List<Spaceship> spaceships = Arrays.asList(
                                  new Spaceship(100.0, new Position(-500.0, -200.0)),
                                  new Spaceship(115.5, new Position(100.0, -100.0)),
                                  new Spaceship(142.7, new Position(500.0, 100.0)));
    Position position = locationService.getPosition(spaceships);

    assertEquals(positionXPredicted, position.getX(), "Trilateration result in X");
    assertEquals(positionYPredicted, position.getY(), "Trilateration result in Y");
  }
}
