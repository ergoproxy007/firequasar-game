package com.dtorres.firequasar.command.domain.service;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
public class LocationService {

  private static final int X_POSITION_INDEX = 0;
  private static final int Y_POSITION_INDEX = 1;

  public CompletionStage<Position> calculatePositionAsync(List<Spaceship> spaceships) {
    return supplyAsync(() -> this.calculatePosition(spaceships));
  }

  private Position calculatePosition(List<Spaceship> spaceships) {
    double[] trilaterationVector = getLocation(getCoordinates(spaceships), getDistances(spaceships));
    return new Position(trilaterationVector[X_POSITION_INDEX], trilaterationVector[Y_POSITION_INDEX]);
  }

  private double[] getLocation(double[][] coordinates, double[] distances) {
    TrilaterationFunction trilateration = new TrilaterationFunction(coordinates, distances);
    NonLinearLeastSquaresSolver nonLinearLeastSquares = new NonLinearLeastSquaresSolver(trilateration, new LevenbergMarquardtOptimizer());
    return nonLinearLeastSquares.solve().getPoint().toArray();
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
