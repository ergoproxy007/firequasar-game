package com.dtorres.firequasar.query.application.handler;

import static com.dtorres.firequasar.query.infrastructure.exception.helper.QueryExceptionHelper.throwObject;

import com.dtorres.firequasar.query.domain.dao.DaoSpaceship;
import com.dtorres.firequasar.query.domain.model.DtoSpaceship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandlerSpaceshipFindByName {

  private final DaoSpaceship daoSpaceship;

  @Autowired
  public HandlerSpaceshipFindByName(DaoSpaceship daoSpaceship) {
    this.daoSpaceship = daoSpaceship;
  }

  public DtoSpaceship execute(String name) {
    return daoSpaceship.findByName(name).exceptionally(throwable -> throwObject(throwable, new DtoSpaceship())).toCompletableFuture().join();
  }

}
