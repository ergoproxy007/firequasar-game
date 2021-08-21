package com.dtorres.firequasar.command.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;

@Repository
public interface SpaceshipLocationRepository extends JpaRepository<SpaceshipLocationEntity, String> {
}
