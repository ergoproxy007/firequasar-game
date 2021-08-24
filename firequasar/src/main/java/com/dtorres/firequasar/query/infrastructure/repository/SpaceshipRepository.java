package com.dtorres.firequasar.query.infrastructure.repository;

import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceshipRepository extends JpaRepository<SpaceshipLocationEntity, String> {

  @Query(value = "select * from spaceship_location where name = :name", nativeQuery = true)
  SpaceshipLocationEntity findByName(@Param("name") String name);

}
