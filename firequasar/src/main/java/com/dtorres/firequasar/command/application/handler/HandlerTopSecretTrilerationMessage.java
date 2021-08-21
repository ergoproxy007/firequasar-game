package com.dtorres.firequasar.command.application.handler;

import com.dtorres.firequasar.command.domain.model.SpaceshipConsolidated;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import org.springframework.stereotype.Component;

@Component
public class HandlerTopSecretTrilerationMessage {

  public TrilerationMessage execute(SpaceshipConsolidated spaceshipConsolidated) {
    return new TrilerationMessage();
  }
}
