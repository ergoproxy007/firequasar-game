package com.dtorres.firequasar.command.application.handler;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.firequasar.command.application.factory.SpaceshipFactory;
import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.command.application.command.SatelliteCommandConsolidated;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import com.dtorres.firequasar.command.domain.service.LocationService;
import com.dtorres.firequasar.command.domain.service.MessageService;
import com.dtorres.firequasar.command.infrastructure.helper.TopSecretExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Component
public class HandlerTopSecretTrilerationMessage {

  private SpaceshipFactory factory;

  private LocationService locationService;
  private MessageService messageService;

  @Autowired
  public HandlerTopSecretTrilerationMessage(SpaceshipFactory factory,
                                            LocationService locationService,
                                            MessageService messageService) {
    this.factory = factory;
    this.locationService = locationService;
    this.messageService = messageService;
  }

  public TrilerationMessage execute(SatelliteCommandConsolidated spaceshipConsolidated) {
    List<Spaceship> spaceships = factory.convertToList(spaceshipConsolidated.getSatellites());
    CompletionStage<Position> positionPromise = getPositionPromise(spaceships);
    CompletionStage<String> messagePromise = getMessagePromise(spaceships);
    return positionPromise.thenCombine(messagePromise, TrilerationMessage::new)
                          .exceptionally(TopSecretExceptionHelper::throwTopSecretException)
                          .toCompletableFuture()
                          .join();
  }

  private CompletionStage<Position> getPositionPromise(List<Spaceship> spaceships) {
    return supplyAsync(() -> locationService.calculatePositionByLocation(spaceships));
  }

  private CompletionStage<String> getMessagePromise(List<Spaceship> spaceships) {
    return supplyAsync(() -> messageService.buildMessage(spaceships));
  }

}
