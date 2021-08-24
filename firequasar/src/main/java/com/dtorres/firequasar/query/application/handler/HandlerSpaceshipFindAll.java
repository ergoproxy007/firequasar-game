package com.dtorres.firequasar.query.application.handler;

import static com.dtorres.firequasar.query.infrastructure.exception.helper.QueryExceptionHelper.throwList;

import com.dtorres.firequasar.query.domain.dao.DaoSpaceship;
import com.dtorres.firequasar.query.domain.model.DtoSpaceship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerSpaceshipFindAll {

  private final DaoSpaceship daoSpaceship;

  @Autowired
  public HandlerSpaceshipFindAll(DaoSpaceship daoSpaceship) {
    this.daoSpaceship = daoSpaceship;
  }

  public List<DtoSpaceship> findAll() {
    return daoSpaceship.findAll().exceptionally(throwable -> throwList(throwable)).toCompletableFuture().join();
  }

}
