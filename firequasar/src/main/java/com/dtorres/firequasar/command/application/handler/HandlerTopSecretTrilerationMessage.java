package com.dtorres.firequasar.command.application.handler;

import static com.dtorres.firequasar.command.infrastructure.exception.helper.TopSecretExceptionHelper.throwObject;

import com.dtorres.firequasar.command.application.factory.SpaceshipFactory;
import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.command.application.command.SatelliteCommandConsolidated;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import com.dtorres.firequasar.command.domain.service.LocationService;
import com.dtorres.firequasar.command.domain.service.MessageService;
import com.dtorres.firequasar.command.domain.service.SpaceshipCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Component
public class HandlerTopSecretTrilerationMessage {

  private final SpaceshipFactory factory;
  private final LocationService locationService;
  private final MessageService messageService;
  private final SpaceshipCacheService spaceshipCacheService;

  @Autowired
  public HandlerTopSecretTrilerationMessage(SpaceshipFactory factory,
                                            LocationService locationService,
                                            MessageService messageService,
                                            SpaceshipCacheService spaceshipCacheService) {
    this.factory = factory;
    this.locationService = locationService;
    this.messageService = messageService;
    this.spaceshipCacheService = spaceshipCacheService;
  }

  public TrilerationMessage execute(SatelliteCommandConsolidated satelliteCommandConsolidated) {
    List<Spaceship> spaceships = spaceshipCacheService.combineWithSpaceships(factory.convertToListBySatelliteCommand(satelliteCommandConsolidated.getSatellites()));
    CompletionStage<Position> positionPromise =  locationService.calculatePosition(spaceships);
    CompletionStage<String> messagePromise = messageService.getMessage(this.getMultidimensionalMessage(spaceships));
    return positionPromise.thenCombine(messagePromise, TrilerationMessage::new)
                          .exceptionally(throwable -> throwObject(throwable, new TrilerationMessage()))
                          .toCompletableFuture()
                          .join();
  }

  private String[][] getMultidimensionalMessage(List<Spaceship> spaceships) {
    return spaceships.stream()
                     .map(Spaceship::getMessages)
                     .collect(Collectors.toList())
                     .stream()
                     .toArray(String[][]::new);
  }
}
