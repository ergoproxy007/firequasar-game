package com.dtorres.firequasar.command.infrastructure.service.cache;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.CompletableFuture.runAsync;

import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.dtorres.firequasar.command.infrastructure.repository.SpaceshipLocationRepository;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class SpaceshipLocationCacheService {

  @Autowired
  private SpaceshipLocationRepository spaceshipLocationRepository;

  public CompletionStage<SpaceshipLocationEntity> save(SpaceshipLocationEntity entity) {
    return supplyAsync(() -> spaceshipLocationRepository.save(entity));
  }

  public CompletionStage<List<SpaceshipLocationEntity>> saveAll(List<SpaceshipLocationEntity> entities) {
    return supplyAsync(() -> spaceshipLocationRepository.saveAll(entities));
  }

  public CompletionStage<List<SpaceshipLocationEntity>> findAll() {
    return supplyAsync(() -> spaceshipLocationRepository.findAll());
  }

  public CompletionStage<Void> update(SpaceshipLocationEntity entity) {
    return runAsync(() -> spaceshipLocationRepository.update(entity.getName(), entity.getDistance(), entity.getMessages()));
  }

  public SpaceshipLocationEntity findByName(String name) {
    return spaceshipLocationRepository.findByName(name);
  }
}
