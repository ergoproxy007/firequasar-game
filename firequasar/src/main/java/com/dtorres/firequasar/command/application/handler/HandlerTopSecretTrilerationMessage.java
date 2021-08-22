package com.dtorres.firequasar.command.application.handler;

import static com.dtorres.firequasar.command.infrastructure.exception.helper.TopSecretExceptionHelper.throwObject;

import com.dtorres.firequasar.command.application.factory.SpaceshipFactory;
import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.command.application.command.SatelliteCommandConsolidated;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import com.dtorres.firequasar.command.domain.service.LocationService;
import com.dtorres.firequasar.command.domain.service.MessageService;
import com.dtorres.firequasar.command.infrastructure.service.cache.CombineCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Component
public class HandlerTopSecretTrilerationMessage {

  private final SpaceshipFactory factory;
  private final LocationService locationService;
  private final MessageService messageService;
  private final CombineCacheService combineCacheService;

  @Autowired
  public HandlerTopSecretTrilerationMessage(SpaceshipFactory factory,
                                            LocationService locationService,
                                            MessageService messageService,
                                            CombineCacheService combineCacheService) {
    this.factory = factory;
    this.locationService = locationService;
    this.messageService = messageService;
    this.combineCacheService = combineCacheService;
  }

  public TrilerationMessage execute(SatelliteCommandConsolidated spaceshipConsolidated) {
    List<Spaceship> spaceships = combineCacheService.combineWithSpaceships(factory.convertToListBySatelliteCommand(spaceshipConsolidated.getSatellites()));
    CompletionStage<Position> positionPromise =  locationService.calculatePositionAsync(spaceships);
    CompletionStage<String> messagePromise = messageService.buildMessage(spaceships);
    return positionPromise.thenCombine(messagePromise, TrilerationMessage::new)
                          .exceptionally(throwable -> throwObject(throwable, new TrilerationMessage()))
                          .toCompletableFuture()
                          .join();
  }

}
