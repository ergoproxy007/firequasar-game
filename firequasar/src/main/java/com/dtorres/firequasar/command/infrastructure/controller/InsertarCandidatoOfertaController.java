package com.dtorres.firequasar.command.infrastructure.controller;

import com.dtorres.firequasar.command.application.command.CandidatoOfertaCommand;
import com.dtorres.firequasar.command.infrastructure.service.CandidatoOfertaService;
import com.dtorres.firequasar.shared.dto.CandidatoOfertaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class InsertarCandidatoOfertaController {

    @Autowired
    private CandidatoOfertaService service;

    @PostMapping(value = "/candidato-oferta",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidatoOfertaResponse> guardarCandidato(
            @RequestBody CandidatoOfertaCommand candidato) {

        Long id = service.insert(candidato);
        return ok(convertirToResponse(id, candidato));
    }

    private CandidatoOfertaResponse convertirToResponse(Long id, CandidatoOfertaCommand candidato) {
        CandidatoOfertaResponse response = new CandidatoOfertaResponse();
        response.setId(id);
        return response;
    }
}
