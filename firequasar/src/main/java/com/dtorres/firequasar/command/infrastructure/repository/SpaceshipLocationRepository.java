package com.dtorres.firequasar.command.infrastructure.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;

import java.util.List;

@Repository
public interface SpaceshipLocationRepository extends JpaRepository<SpaceshipLocationEntity, String> {

  @Query(value = "select * from spaceship_location where name = :name", nativeQuery = true)
  SpaceshipLocationEntity findByName(@Param("name") String name);

  @Modifying
  @Transactional
  @Query(value = "update spaceship_location set distance = :distance, messages = :messages where name = :name", nativeQuery = true)
  void update(@Param(value = "name") String name, @Param(value = "distance") Double distance, @Param(value = "messages") String messages);
}
