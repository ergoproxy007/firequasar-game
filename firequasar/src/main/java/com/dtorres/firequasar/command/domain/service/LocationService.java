package com.dtorres.firequasar.command.domain.service;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
public class LocationService {

  private static final int X_POSITION_INDEX = 0;
  private static final int Y_POSITION_INDEX = 1;

  private final TrilaterationService trilaterationService;

  @Autowired
  public LocationService(TrilaterationService trilaterationService) {
    this.trilaterationService = trilaterationService;
  }

  public CompletionStage<Position> calculatePosition(List<Spaceship> spaceships) {
    return supplyAsync(() -> this.getPosition(spaceships));
  }

  private Position getPosition(List<Spaceship> spaceships) {
    double[] trilaterationVector = trilaterationService.getLocation(getCoordinates(spaceships), getDistances(spaceships));
    return new Position(trilaterationVector[X_POSITION_INDEX], trilaterationVector[Y_POSITION_INDEX]);
  }

  private double[][] getCoordinates(List<Spaceship> spaceships) {
    return spaceships.stream()
                     .map(Spaceship::getPrimitivePosition)
                     .collect(Collectors.toList())
                     .stream()
                     .toArray(double[][]::new);
  }

  private double[] getDistances(List<Spaceship> spaceships) {
    return spaceships.stream().mapToDouble(Spaceship::getDistance).toArray();
  }

}
