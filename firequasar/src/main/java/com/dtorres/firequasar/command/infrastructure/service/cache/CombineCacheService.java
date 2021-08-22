package com.dtorres.firequasar.command.infrastructure.service.cache;

import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static com.dtorres.firequasar.command.infrastructure.exception.helper.TopSecretExceptionHelper.throwList;

@Service
public class CombineCacheService {

  @Autowired
  private SpaceshipLocationCacheService spaceshipLocationCacheService;

  public List<Spaceship> combineWithSpaceships(List<Spaceship> spaceshipsRequest) {

    CompletionStage<List<SpaceshipLocationEntity>> entitiesCachePromise = spaceshipLocationCacheService.findAll();
    return entitiesCachePromise.thenApplyAsync(entitiesCache -> this.mergePositionOfSpaceships(entitiesCache, spaceshipsRequest))
                               .exceptionally(throwable -> throwList(throwable))
                               .toCompletableFuture()
                               .join();
  }

  private List<Spaceship> mergePositionOfSpaceships(List<SpaceshipLocationEntity> entitiesCache, List<Spaceship> spaceshipsRequest) {
    return spaceshipsRequest.stream()
                            .map(spaceship -> {
                              Spaceship spaceshipWithPosition = entitiesCache.stream()
                                .filter(entityCache -> entityCache.getName().equalsIgnoreCase(spaceship.getName()))
                                .map(entityCache -> Spaceship.create(entityCache.getName(), entityCache.getCoordinateX(), entityCache.getCoordinateY()))
                                .findFirst()
                                .orElse(spaceship);
                              spaceship.setPosition(spaceshipWithPosition.getPosition());
                              return spaceship;
                            })
                            .collect(Collectors.toList());
  }
}
