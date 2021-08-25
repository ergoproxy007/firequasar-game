package com.dtorres.firequasar.command.infrastructure.controller;

import static com.dtorres.firequasar.helper.MultiMessageHelper.getKenobiMessage;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_KENOBI;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.KENOBI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpStatus.OK;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.dtorres.firequasar.command.application.command.SatelliteCommand;
import com.dtorres.firequasar.command.application.factory.SpaceshipFactory;
import com.dtorres.firequasar.command.application.handler.HandlerTopSecretSplitSaveSpaceship;
import com.dtorres.firequasar.command.domain.dto.DtoSpaceshipSavedResponse;
import com.dtorres.firequasar.command.domain.enums.TopSecretSplitStatusResponse;
import com.dtorres.firequasar.command.infrastructure.service.cache.SpaceshipLocationCacheService;
import com.dtorres.firequasar.command.infrastructure.service.cache.TransactionSpaceshipCacheService;
import com.dtorres.firequasar.commons.domain.exceptions.TopSecretException;
import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TopSecretSplitCommandControllerTest {

  private static final String OBJECT_MESSAGE = "Exception when getting the object";

  private TopSecretSplitCommandController controller;
  private HandlerTopSecretSplitSaveSpaceship handlerTopSecretSplitSaveSpaceship;
  private TransactionSpaceshipCacheService transactionSpaceshipCacheService;

  @Mock
  private SpaceshipFactory factory;
  @Mock
  private SpaceshipLocationCacheService spaceshipLocationCacheService;

  @BeforeEach
  public void init() {
    when(factory.convertBySatelliteCommand(any())).thenCallRealMethod();

    this.transactionSpaceshipCacheService = new TransactionSpaceshipCacheService(spaceshipLocationCacheService);
    this.handlerTopSecretSplitSaveSpaceship = new HandlerTopSecretSplitSaveSpaceship(factory, transactionSpaceshipCacheService);
    this.controller = new TopSecretSplitCommandController(handlerTopSecretSplitSaveSpaceship);
  }

  @Test
  public void checkStatusOKResponseProccesSplitUpdateTest() {
    //Arrange
    SatelliteCommand satellite = new SatelliteCommand(KENOBI, DISTANCE_KENOBI, getKenobiMessage());
    String messages = String.join(",", satellite.getMessage());
    SpaceshipLocationEntity entity = new SpaceshipLocationEntity(satellite.getName(), satellite.getDistance(), messages);

    when(spaceshipLocationCacheService.findByName(any())).thenReturn(entity);
    when(spaceshipLocationCacheService.update(any())).thenReturn(CompletableFuture.allOf());
    //Act
    ResponseEntity<DtoSpaceshipSavedResponse> response = controller.proccesTopSecretSplit(KENOBI, satellite);
    //Assert
    assertNotNull(response);
    assertEquals(OK, response.getStatusCode());
    assertEquals(TopSecretSplitStatusResponse.SAVED.toString(), response.getBody().getResult());
    verify(spaceshipLocationCacheService, times(1)).update(any());
  }

  @Test
  public void checkStatusOKResponseProccesSplitSaveTest() {
    //Arrange
    SatelliteCommand satellite = new SatelliteCommand(KENOBI, DISTANCE_KENOBI, getKenobiMessage());
    String messages = String.join(",", satellite.getMessage());
    SpaceshipLocationEntity entity = new SpaceshipLocationEntity(satellite.getName(), satellite.getDistance(), messages);

    when(spaceshipLocationCacheService.findByName(any())).thenReturn(null);
    when(spaceshipLocationCacheService.save(any())).thenReturn(CompletableFuture.completedFuture(entity));
    //Act
    ResponseEntity<DtoSpaceshipSavedResponse> response = controller.proccesTopSecretSplit(KENOBI, satellite);
    //Assert
    assertNotNull(response);
    assertEquals(OK, response.getStatusCode());
    assertEquals(TopSecretSplitStatusResponse.SAVED.toString(), response.getBody().getResult());
    verify(spaceshipLocationCacheService, times(1)).save(any());
  }

  @Test
  public void thrownTopSecretSplitExceptionTest() {
    //Arrange
    when(spaceshipLocationCacheService.findByName(any())).thenReturn(null);
    when(spaceshipLocationCacheService.save(any())).thenReturn(CompletableFuture.completedFuture(null));
    SatelliteCommand satellite = new SatelliteCommand(KENOBI, DISTANCE_KENOBI, getKenobiMessage());
    //Act - Assert
    CompletionException thrown = assertThrows(CompletionException.class, () -> controller.proccesTopSecretSplit(KENOBI, satellite));
    assertTrue(thrown.getCause() instanceof TopSecretException);
    assertEquals(OBJECT_MESSAGE, thrown.getCause().getMessage());
  }

}
