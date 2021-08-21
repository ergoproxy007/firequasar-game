package com.dtorres.firequasar.command.infrastructure.controller;

import com.dtorres.firequasar.command.application.handler.HandlerTopSecretTrilerationMessage;
import com.dtorres.firequasar.command.application.command.SatelliteCommandConsolidated;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TopSecretCommandControllerTest {

  private TopSecretCommandController controller;

  @InjectMocks
  private HandlerTopSecretTrilerationMessage handlerTopSecretTrilerationMessage;

  @BeforeEach
  public void init() {
    this.controller = new TopSecretCommandController(handlerTopSecretTrilerationMessage);
  }

  @Test
  public void checkStatusOKResponseProccesTrilerationMessageSpaceshipTest() {
    ResponseEntity<TrilerationMessage> trilerationMessage = controller.proccesTrilerationMessageSpaceship(new SatelliteCommandConsolidated());

  }
}
