package com.dtorres.firequasar.command.infrastructure.service.trileration;

import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.dtorres.firequasar.helper.MultiMessageHelper.*;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.*;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_SATO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NonLinearTrilaterationServiceTest {

  private NonLinearTrilaterationService nonLinearTrilaterationService;

  @BeforeEach
  public void init() {
    this.nonLinearTrilaterationService = new NonLinearTrilaterationService();
  }

  private List<Spaceship> getSpaceships() {
    return new TestDataBuilderSpaceship()
      .add(KENOBI, DISTANCE_KENOBI, getKenobiMessage(), new Position(-500.0, -200.0))
      .add(SATO, DISTANCE_SATO, getSatoMessage(), new Position(500.0, 100.0))
      .build();
  }

  private double[][] getCoordinates() {
    return getSpaceships().stream()
      .map(Spaceship::getPrimitivePosition)
      .collect(Collectors.toList())
      .stream()
      .toArray(double[][]::new);
  }

  @Test
  public void getLocationTest() {
    double[] distances = new double[] {DISTANCE_KENOBI, DISTANCE_SATO};
    double[] trileration = this.nonLinearTrilaterationService.getLocation(getCoordinates(), distances);
    assertEquals(-60.0414461505287, trileration[0]);
    assertEquals(-68.01240725647044, trileration[1]);
  }
}
