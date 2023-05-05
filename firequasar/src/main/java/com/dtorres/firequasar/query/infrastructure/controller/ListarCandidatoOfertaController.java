package com.dtorres.firequasar.query.infrastructure.controller;

import com.dtorres.firequasar.command.infrastructure.service.CandidatoOfertaService;
import com.dtorres.firequasar.shared.dto.CandidatoOfertaResponse;
import com.dtorres.firequasar.shared.entity.CandidatoOfertaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class ListarCandidatoOfertaController {

    @Autowired
    private CandidatoOfertaService service;

    @GetMapping(value = "/candidato-oferta/listado",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CandidatoOfertaResponse>> listar() {
        return ok(getDataService());
    }

    private List<CandidatoOfertaResponse> getDataService() {
        List<CandidatoOfertaEntity> entities = service.listar();
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::convertirToResponse)
                .collect(Collectors.toList());
    }

    private CandidatoOfertaResponse convertirToResponse(CandidatoOfertaEntity entity) {
        CandidatoOfertaResponse response = new CandidatoOfertaResponse();
        response.setId(entity.getId());
        response.setNombre(entity.getNombre());
        response.setApellido(entity.getApellido());
        response.setCelular(entity.getCelular());
        response.setEmail(entity.getEmail());
        response.setProfile(entity.getProfile());
        response.setPresentacion(entity.getPresentacion());
        response.setDate(entity.getDateTime());
        return response;
    }
}
