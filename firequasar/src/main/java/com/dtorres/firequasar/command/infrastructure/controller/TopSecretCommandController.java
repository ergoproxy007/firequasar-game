package com.dtorres.firequasar.command.infrastructure.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.dtorres.firequasar.command.application.handler.HandlerTopSecretTrilerationMessage;
import com.dtorres.firequasar.command.domain.model.SpaceahipConsolidated;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TopSecretCommandController {

  private final HandlerTopSecretTrilerationMessage handlerTopSecretTrilerationMessage;

  @Autowired
  public TopSecretCommandController(HandlerTopSecretTrilerationMessage handlerTopSecretTrilerationMessage) {

    this.handlerTopSecretTrilerationMessage = handlerTopSecretTrilerationMessage;
  }

  @PostMapping(value = "/topsecret",
               consumes = APPLICATION_JSON_VALUE,
               produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<TrilerationMessage> proccesTrilerationMessageSpaceship(RequestEntity<SpaceahipConsolidated> requestSpaceahipConsolidated) {
    return ok(handlerTopSecretTrilerationMessage.execute(requestSpaceahipConsolidated.getBody()));
  }

}
