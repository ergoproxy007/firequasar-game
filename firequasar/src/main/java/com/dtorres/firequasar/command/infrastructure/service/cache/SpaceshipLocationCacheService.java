package com.dtorres.firequasar.command.infrastructure.service.cache;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtorres.firequasar.command.infrastructure.repository.SpaceshipLocationRepository;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class SpaceshipLocationCacheService {

  @Autowired
  private SpaceshipLocationRepository spaceshipLocationRepository;

  public CompletionStage<List<SpaceshipLocationEntity>> save(List<SpaceshipLocationEntity> entities) {
    return supplyAsync(() -> spaceshipLocationRepository.saveAll(entities));
  }

  public CompletionStage<List<SpaceshipLocationEntity>> findAll() {
    return supplyAsync(() -> spaceshipLocationRepository.findAll());
  }
}
