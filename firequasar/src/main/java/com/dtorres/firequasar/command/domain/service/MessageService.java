package com.dtorres.firequasar.command.domain.service;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.dtorres.firequasar.command.domain.model.Spaceship;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class MessageService {

  public CompletionStage<String> buildMessage(List<Spaceship> spaceships) {
    return supplyAsync(() -> new String());
  }

  private String getMessage(String[]... messages) {
    return new String();
  }
}
