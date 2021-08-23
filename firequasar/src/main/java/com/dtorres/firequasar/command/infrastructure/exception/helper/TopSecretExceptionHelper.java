package com.dtorres.firequasar.command.infrastructure.exception.helper;

import static java.util.Collections.emptyList;

import java.util.List;
import com.dtorres.firequasar.commons.domain.exceptions.TopSecretException;
import com.dtorres.firequasar.shared.log.LogErrorManager;

public class TopSecretExceptionHelper {

  private static final String OBJECT_MESSAGE = "Exception when getting the object";
  private static final String LIST_MESSAGE = "Exception when getting the list";

  public static <T> T throwObject(Throwable throwable, Object object) {
    if(throwable != null) {
      LogErrorManager.error(OBJECT_MESSAGE, throwable);
      throw new TopSecretException(OBJECT_MESSAGE);
    }
    return (T) object;
  }

  public static <T> List<T> throwList(Throwable throwable) {
    if(throwable != null) {
      LogErrorManager.error(LIST_MESSAGE, throwable);
      throw new TopSecretException(throwable.getMessage());
    }
    return emptyList();
  }
}
