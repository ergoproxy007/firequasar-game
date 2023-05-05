package com.dtorres.firequasar.command.infrastructure.old.controller;

import com.dtorres.firequasar.command.application.command.SatelliteCommand;
import com.dtorres.firequasar.command.application.handler.HandlerTopSecretSplitSaveSpaceship;
import com.dtorres.firequasar.command.application.handler.HandlerTopSecretTrilerationMessage;
import com.dtorres.firequasar.command.domain.dto.DtoSpaceshipSavedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class TopSecretSplitCommandController {

  private static final String TOP_SECRET_SPLIT_ROUTE = "/topsecret_split/{satellite_name}";

  private final HandlerTopSecretSplitSaveSpaceship handlerTopSecretSplitSaveSpaceship;

  @Autowired
  public TopSecretSplitCommandController(HandlerTopSecretSplitSaveSpaceship handlerTopSecretSplitSaveSpaceship) {

    this.handlerTopSecretSplitSaveSpaceship = handlerTopSecretSplitSaveSpaceship;
  }

  @PostMapping(value = TOP_SECRET_SPLIT_ROUTE,
              consumes = APPLICATION_JSON_VALUE,
              produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<DtoSpaceshipSavedResponse> proccesTopSecretSplit(
          @PathVariable(name = "satellite_name", required = true) String satelliteName,
          @RequestBody SatelliteCommand satelliteCommand) {

    satelliteCommand.setName(satelliteName);

    return ok(handlerTopSecretSplitSaveSpaceship.execute(satelliteCommand));
  }

}
