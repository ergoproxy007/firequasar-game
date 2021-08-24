package com.dtorres.firequasar.command.domain.service;

import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import java.util.concurrent.CompletionStage;

public interface CommandCacheService {

  CompletionStage<SpaceshipLocationEntity> save(Spaceship spaceship);
}
