package com.dtorres.firequasar.query.infrastructure.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.dtorres.firequasar.query.application.handler.HandlerSpaceshipFindAll;
import com.dtorres.firequasar.query.application.handler.HandlerSpaceshipFindByName;
import com.dtorres.firequasar.query.domain.model.DtoSpaceship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SpaceshipQueryController {

  private static final String TOP_SECRET_SPLIT_ROUTE = "/topsecret_split/{satellite_name}";
  private static final String FIND_ALL_SPACESHIP_ROUTE = "/spaceships";

  private final HandlerSpaceshipFindByName handlerSpaceshipFindByName;
  private final HandlerSpaceshipFindAll handlerSpaceshipFindAll;

  @Autowired
  public SpaceshipQueryController(HandlerSpaceshipFindByName handlerSpaceshipFindByName, HandlerSpaceshipFindAll handlerSpaceshipFindAll) {

    this.handlerSpaceshipFindByName = handlerSpaceshipFindByName;
    this.handlerSpaceshipFindAll = handlerSpaceshipFindAll;
  }

  @GetMapping(value = TOP_SECRET_SPLIT_ROUTE,
              consumes = APPLICATION_JSON_VALUE,
              produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<DtoSpaceship> findByName(@PathVariable(name = "satellite_name", required = true) String satelliteName) {

    return ok(handlerSpaceshipFindByName.execute(satelliteName));
  }

  @GetMapping(value = FIND_ALL_SPACESHIP_ROUTE,
              consumes = APPLICATION_JSON_VALUE,
              produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DtoSpaceship>> findAllSpaceship() {

    return ok(handlerSpaceshipFindAll.findAll());
  }

}
