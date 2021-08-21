package com.dtorres.firequasar.command.infrastructure.helper;

import com.dtorres.firequasar.command.domain.exception.TopSecretException;
import com.dtorres.firequasar.command.domain.model.TrilerationMessage;
import com.dtorres.firequasar.shared.log.LogErrorManager;

public class TopSecretExceptionHelper {

  private static final String TOPSECRET_MESSAGE = "Exception in TopSecret";

  public static TrilerationMessage throwTopSecretException(Throwable throwable) {
    if(throwable != null) {
      LogErrorManager.error(TOPSECRET_MESSAGE, throwable);
      throw new TopSecretException(throwable.getMessage());
    }
    return new TrilerationMessage();
  }
}
