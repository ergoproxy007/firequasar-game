package com.dtorres.firequasar.query.infrastructure.dao;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.firequasar.commons.domain.exceptions.NotFoundDataException;
import com.dtorres.firequasar.query.domain.dao.DaoSpaceship;
import com.dtorres.firequasar.query.domain.model.DtoSpaceship;
import com.dtorres.firequasar.query.infrastructure.repository.SpaceshipRepository;
import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
public class DaoSpaceshipImpl implements DaoSpaceship {

  @Autowired
  private SpaceshipRepository spaceshipRepository;

  @Override
  public CompletionStage<DtoSpaceship> findByName(String name) {
    SpaceshipLocationEntity entity = spaceshipRepository.findByName(name);
    return supplyAsync(() -> this.getDtoSpaceship(entity));
  }

  @Override
  public CompletionStage<List<DtoSpaceship>> findAll() {
    List<SpaceshipLocationEntity> spaceshipEntities = spaceshipRepository.findAll();
    return supplyAsync(() -> spaceshipEntities.stream().map(this::getDtoSpaceship).collect(Collectors.toList()));
  }

  private DtoSpaceship getDtoSpaceship(SpaceshipLocationEntity entity) {
    if(entity != null) {
      String[] messageSeparate = entity.getMessages() != null ? entity.getMessages().split(",") : new String[0];
      return new DtoSpaceship(entity.getName(), entity.getDistance(), messageSeparate, entity.getCoordinateX(), entity.getCoordinateY());
    }
    throw new NotFoundDataException("Spaceship could not be found");
  }
}
