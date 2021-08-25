package com.dtorres.firequasar.query.infrastructure.controller;

import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.DISTANCE_SATO;
import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.SATO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

import com.dtorres.firequasar.query.domain.model.DtoSpaceship;
import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.dtorres.firequasar.query.application.handler.HandlerSpaceshipFindAll;
import com.dtorres.firequasar.query.application.handler.HandlerSpaceshipFindByName;
import com.dtorres.firequasar.query.infrastructure.dao.DaoSpaceshipImpl;
import com.dtorres.firequasar.query.infrastructure.repository.SpaceshipRepository;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SpaceshipQueryControllerTest {

  private SpaceshipQueryController controller;
  private HandlerSpaceshipFindAll handlerSpaceshipFindAll;
  private DaoSpaceshipImpl daoSpaceshipImpl;

  @Mock
  private SpaceshipRepository spaceshipRepository;

  @BeforeEach
  public void init() {
    this.daoSpaceshipImpl = new DaoSpaceshipImpl(spaceshipRepository);
    HandlerSpaceshipFindByName handlerSpaceshipFindByName = new HandlerSpaceshipFindByName(daoSpaceshipImpl);
    this.handlerSpaceshipFindAll = new HandlerSpaceshipFindAll(daoSpaceshipImpl);
    this.controller = new SpaceshipQueryController(handlerSpaceshipFindByName, handlerSpaceshipFindAll);
  }

  @Test
  public void findByNameTest() {
    //Arrange
    String satelliteName = SATO;
    SpaceshipLocationEntity entity = new SpaceshipLocationEntity(SATO, DISTANCE_SATO, "");
    when(spaceshipRepository.findByName(any())).thenReturn(entity);
    //Act
    ResponseEntity<DtoSpaceship> result = this.controller.findByName(satelliteName);
    //Assert
    assertNotNull(result);
    assertEquals(satelliteName, result.getStatusCode());
    assertEquals(SATO, result.getBody().getName(),"satellite name");
    assertEquals(DISTANCE_SATO, result.getBody().getDistance(),"satellite distance");
    verify(spaceshipRepository, times(1)).findByName(any());
  }

  @Test
  public void findAllSpaceshipTest() {
    //Arrange
    String satelliteName = SATO;
    SpaceshipLocationEntity entity = new SpaceshipLocationEntity(SATO, DISTANCE_SATO, "");
    when(spaceshipRepository.findAll()).thenReturn(Collections.singletonList(entity));
    //Act
    ResponseEntity<List<DtoSpaceship>> result = this.controller.findAllSpaceship();
    //Assert
    assertNotNull(result);
    assertEquals(OK, result.getStatusCode());
    verify(spaceshipRepository, times(1)).findAll();
  }
}
