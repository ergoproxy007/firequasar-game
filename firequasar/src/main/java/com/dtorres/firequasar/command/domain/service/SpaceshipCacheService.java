package com.dtorres.firequasar.command.domain.service;

import com.dtorres.firequasar.command.domain.model.Spaceship;

import java.util.List;

public interface SpaceshipCacheService {

  List<Spaceship> combineWithSpaceships(List<Spaceship> spaceshipsRequest);
}
