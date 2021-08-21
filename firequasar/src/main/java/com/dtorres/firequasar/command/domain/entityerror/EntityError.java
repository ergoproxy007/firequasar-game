package com.dtorres.firequasar.command.domain.entityerror;

import com.dtorres.firequasar.command.domain.exception.NotAcceptedAttributeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityError {

  private List<String> messages;

  public EntityError() {
    this.messages = new ArrayList<>();
  }

  public void add(String message) {
    this.messages.add(message);
  }

  public void validateIsNullOrEmpty(Object object, String message) {
    if(isNullOrEmpty(object)) {
      add(message);
    }
  }

  public void hasErrorMessages() {
    if(!messages.isEmpty()) {
      throw new NotAcceptedAttributeException(messages.stream().collect(Collectors.joining(",")));
    }
  }

  private boolean isNullOrEmpty(Object object) {
    return object == null || object.toString().trim().isEmpty();
  }
}
