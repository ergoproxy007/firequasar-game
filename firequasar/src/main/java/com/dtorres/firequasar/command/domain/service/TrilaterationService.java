package com.dtorres.firequasar.command.domain.service;

public interface TrilaterationService {

  double[] getLocation(double[][] coordinates, double[] distances);
}
