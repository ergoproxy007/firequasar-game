package com.dtorres.firequasar.controllers;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping(value = "obtener")
  public ResponseEntity<List<DtoTest>> findAll() {
    List<DtoTest> lista = new ArrayList<>();
    lista.add(new DtoTest(100L, "dan"));
    return ok(lista);
  }
}
