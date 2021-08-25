package com.dtorres.firequasar.command.infrastructure.service.cache;

import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.command.domain.service.CommandCacheService;
import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class TransactionSpaceshipCacheService implements CommandCacheService {

  private final SpaceshipLocationCacheService spaceshipLocationCacheService;

  @Autowired
  public TransactionSpaceshipCacheService(SpaceshipLocationCacheService spaceshipLocationCacheService) {
    this.spaceshipLocationCacheService = spaceshipLocationCacheService;
  }

  @Override
  public CompletionStage<SpaceshipLocationEntity> save(Spaceship spaceship) {
    SpaceshipLocationEntity entityFound = spaceshipLocationCacheService.findByName(spaceship.getName());
    if(entityFound == null) {
      String messages = String.join(",", spaceship.getMessages());
      return spaceshipLocationCacheService.save(new SpaceshipLocationEntity(spaceship.getName(), spaceship.getDistance(), messages));
    }
    return update(spaceship);
  }

  private CompletionStage<SpaceshipLocationEntity> update(Spaceship spaceship) {
    String messages = String.join(",", spaceship.getMessages());
    SpaceshipLocationEntity entity = new SpaceshipLocationEntity(spaceship.getName(), spaceship.getDistance(), messages);
    CompletionStage<SpaceshipLocationEntity> promiseLazy = CompletableFuture.completedFuture(entity);
    return spaceshipLocationCacheService.update(entity).thenCombine(promiseLazy, (aVoid, entityUpdated) -> entityUpdated);
  }
}
