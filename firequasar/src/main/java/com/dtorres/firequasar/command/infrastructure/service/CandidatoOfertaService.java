package com.dtorres.firequasar.command.infrastructure.service;

import com.dtorres.firequasar.command.application.command.CandidatoOfertaCommand;
import com.dtorres.firequasar.command.infrastructure.repository.CandidatoOfertaRepository;
import com.dtorres.firequasar.shared.entity.CandidatoOfertaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoOfertaService {

    @Autowired
    private CandidatoOfertaRepository repository;

    public Long insert(CandidatoOfertaCommand candidato) {
        CandidatoOfertaEntity candidatoSaved = repository.save(this.convertirToEntity(candidato));
        return candidatoSaved.getId();
    }

    private CandidatoOfertaEntity convertirToEntity(CandidatoOfertaCommand candidato) {
        return new CandidatoOfertaEntity(
                candidato.getNombre(), candidato.getApellido(), candidato.getCelular(),
                candidato.getEmail(), candidato.getProfile(), candidato.getPresentacion());
    }

    public List<CandidatoOfertaEntity> listar() {
        return repository.findAll();
    }
}
