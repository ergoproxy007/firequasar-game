package com.dtorres.firequasar.command.infrastructure.repository;

import com.dtorres.firequasar.shared.entity.CandidatoOfertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoOfertaRepository extends JpaRepository<CandidatoOfertaEntity, Long> {
}
