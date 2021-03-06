package com.dtorres.firequasar.command.infrastructure.controller;

import static com.dtorres.firequasar.helper.MultiMessageHelper.getKenobiMessage;
import static com.dtorres.firequasar.helper.MultiMessageHelper.getSkywalkerMessage;
import static com.dtorres.firequasar.helper.MultiMessageHelper.getSatoMessage;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.SKYWALKER;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_SKYWALKER;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_KENOBI;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.KENOBI;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.SATO;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_SATO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpStatus.OK;

import com.dtorres.firequasar.command.application.factory.SpaceshipFactory;
import com.dtorres.firequasar.command.application.handler.HandlerTopSecretTrilerationMessage;
import com.dtorres.firequasar.command.application.command.SatelliteCommandConsolidated;
import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import com.dtorres.firequasar.command.domain.service.LocationService;
import com.dtorres.firequasar.command.domain.service.MessageService;
import com.dtorres.firequasar.command.infrastructure.service.cache.CombineSpaceshipCacheService;
import com.dtorres.firequasar.command.infrastructure.service.trileration.NonLinearTrilaterationService;
import com.dtorres.firequasar.commons.domain.exceptions.TopSecretException;
import com.dtorres.firequasar.testdatabuilder.command.TestDataBuilderSatelliteCommandConsolidated;
import com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletionException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TopSecretCommandControllerTest {

  private static final String OBJECT_MESSAGE = "Exception when getting the object";

  private TopSecretCommandController controller;
  private HandlerTopSecretTrilerationMessage handlerTopSecretTrilerationMessage;
  private LocationService locationService;

  @Mock
  private SpaceshipFactory factory;
  @Mock
  private NonLinearTrilaterationService trilaterationService;
  @Mock
  private MessageService messageService;
  @Mock
  private CombineSpaceshipCacheService spaceshipCacheService;

  @BeforeEach
  public void init() {
    when(factory.convertToListBySatelliteCommand(any())).thenCallRealMethod();
    when(messageService.getMessage(any())).thenCallRealMethod();
    when(trilaterationService.getLocation(any(), any())).thenCallRealMethod();

    this.locationService = new LocationService(trilaterationService);
    this.handlerTopSecretTrilerationMessage = new HandlerTopSecretTrilerationMessage(factory, locationService, messageService, spaceshipCacheService);
    this.controller = new TopSecretCommandController(handlerTopSecretTrilerationMessage);
  }

  private List<Spaceship> getSpaceships() {
    return new TestDataBuilderSpaceship()
                .add(KENOBI, DISTANCE_KENOBI, getKenobiMessage(), new Position(-500.0, -200.0))
                .add(SKYWALKER, DISTANCE_SKYWALKER, getSkywalkerMessage(), new Position(100.0, -100.0))
                .add(SATO, DISTANCE_SATO, getSatoMessage(), new Position(500.0, 100.0))
                .build();
  }

  @Test
  public void checkStatusOKResponseProccesTrilerationMessageSpaceshipTest() {
    //Arrange
    String messagePredicted = "este es un mensaje secreto";
    when(spaceshipCacheService.combineWithSpaceships(any())).thenReturn(getSpaceships());

    SatelliteCommandConsolidated satelliteCommand = new TestDataBuilderSatelliteCommandConsolidated()
                                                        .add(KENOBI, DISTANCE_KENOBI, getKenobiMessage())
                                                        .add(SKYWALKER, DISTANCE_SKYWALKER, getSkywalkerMessage())
                                                        .add(SATO, DISTANCE_SATO, getSatoMessage())
                                                        .build();
    //Act
    ResponseEntity<TrilerationMessage> trilerationMessage = controller.proccesTopSecretTrilerationMessage(satelliteCommand);
    //Assert
    assertNotNull(trilerationMessage);
    assertEquals(OK, trilerationMessage.getStatusCode());
    assertEquals(messagePredicted, trilerationMessage.getBody().getMessage(),"message is decoded");
    verify(spaceshipCacheService, times(1)).combineWithSpaceships(any());
  }

  @Test
  public void thrownTopSecretExceptionTest() {
    //Arrange
    List<Spaceship> spaceships = new TestDataBuilderSpaceship().add(KENOBI, DISTANCE_KENOBI, getKenobiMessage(),  null).build();
    when(spaceshipCacheService.combineWithSpaceships(any())).thenReturn(spaceships);

    SatelliteCommandConsolidated satelliteCommand = new TestDataBuilderSatelliteCommandConsolidated().add(KENOBI, null, null).build();
    CompletionException thrown = assertThrows(CompletionException.class, () -> controller.proccesTopSecretTrilerationMessage(satelliteCommand));
    assertTrue(thrown.getCause() instanceof TopSecretException);
    assertEquals(OBJECT_MESSAGE, thrown.getCause().getMessage());
  }

}
