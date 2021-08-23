package com.dtorres.firequasar.command.infrastructure.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.dtorres.firequasar.command.application.handler.HandlerTopSecretTrilerationMessage;
import com.dtorres.firequasar.command.application.command.SatelliteCommandConsolidated;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TopSecretCommandController {

  private static final String TOP_SECRET_ROUTE = "/topsecret";

  private final HandlerTopSecretTrilerationMessage handlerTopSecretTrilerationMessage;

  @Autowired
  public TopSecretCommandController(HandlerTopSecretTrilerationMessage handlerTopSecretTrilerationMessage) {

    this.handlerTopSecretTrilerationMessage = handlerTopSecretTrilerationMessage;
  }

  @PostMapping(value = TOP_SECRET_ROUTE,
               consumes = APPLICATION_JSON_VALUE,
               produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<TrilerationMessage> proccesTrilerationMessageSpaceship(@RequestBody SatelliteCommandConsolidated satelliteCommandConsolidated) {
    return ok(handlerTopSecretTrilerationMessage.execute(satelliteCommandConsolidated));
  }

}
