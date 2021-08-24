package com.dtorres.firequasar.query.domain.dao;

import com.dtorres.firequasar.query.domain.model.DtoSpaceship;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface DaoSpaceship {

  CompletionStage<DtoSpaceship> findByName(String name);

  CompletionStage<List<DtoSpaceship>> findAll();
}
