package com.dtorres.firequasar.command.application.handler;

import com.dtorres.firequasar.command.application.command.SatelliteCommand;
import com.dtorres.firequasar.command.application.factory.SpaceshipFactory;
import com.dtorres.firequasar.command.domain.dto.DtoSpaceshipSavedResponse;
import com.dtorres.firequasar.command.domain.enums.TopSecretSplitStatusResponse;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.command.domain.service.CommandCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.dtorres.firequasar.command.infrastructure.exception.helper.TopSecretExceptionHelper.throwObject;

@Component
public class HandlerTopSecretSplitSaveSpaceship {

  private static final TopSecretSplitStatusResponse SAVED = TopSecretSplitStatusResponse.SAVED;

  private final SpaceshipFactory factory;
  private final CommandCacheService commandCacheService;

  @Autowired
  public HandlerTopSecretSplitSaveSpaceship(SpaceshipFactory factory,
                                            CommandCacheService commandCacheService) {
    this.factory = factory;
    this.commandCacheService = commandCacheService;
  }

  public DtoSpaceshipSavedResponse execute(SatelliteCommand satelliteCommand) {
    Spaceship spaceship = factory.convertBySatelliteCommand(satelliteCommand);
    return commandCacheService.save(spaceship)
                              .thenApplyAsync(entity -> new DtoSpaceshipSavedResponse(entity.getName(), SAVED.toString()))
                              .exceptionally(throwable -> throwObject(throwable, new DtoSpaceshipSavedResponse()))
                              .toCompletableFuture()
                              .join();
  }

}
