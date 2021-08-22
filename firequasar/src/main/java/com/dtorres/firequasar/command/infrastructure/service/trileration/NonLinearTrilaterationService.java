package com.dtorres.firequasar.command.infrastructure.service.trileration;

import com.dtorres.firequasar.command.domain.service.TrilaterationService;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

@Service
public class NonLinearTrilaterationService implements TrilaterationService {

  public double[] getLocation(double[][] coordinates, double[] distances) {
    TrilaterationFunction trilateration = new TrilaterationFunction(coordinates, distances);
    NonLinearLeastSquaresSolver nonLinearLeastSquares = new NonLinearLeastSquaresSolver(trilateration, new LevenbergMarquardtOptimizer());
    return nonLinearLeastSquares.solve().getPoint().toArray();
  }
}
