package com.dtorres.firequasar.command.infrastructure.service;

import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtorres.firequasar.command.infrastructure.repository.SpaceshipLocationRepository;

import java.util.List;

@Service
public class SpaceshipLocationService {

  @Autowired
  private SpaceshipLocationRepository spaceshipLocationRepository;

  public void save(List<SpaceshipLocationEntity> entities) {
    spaceshipLocationRepository.saveAll(entities);
  }

  public List<SpaceshipLocationEntity> findAll() {
    return spaceshipLocationRepository.findAll();
  }
}
