package com.dtorres.firequasar.command.application.handler;

import com.dtorres.firequasar.command.domain.model.SpaceahipConsolidated;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import org.springframework.stereotype.Component;

@Component
public class HandlerTopSecretTrilerationMessage {

  public TrilerationMessage execute(SpaceahipConsolidated spaceahipConsolidated) {
    return new TrilerationMessage();
  }
}
