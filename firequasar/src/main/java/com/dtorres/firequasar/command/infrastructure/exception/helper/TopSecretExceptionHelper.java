package com.dtorres.firequasar.command.infrastructure.exception.helper;

import java.util.List;
import com.dtorres.firequasar.commons.domain.exceptions.TopSecretException;
import com.dtorres.firequasar.commons.helper.ThrowExceptionHelper;

public class TopSecretExceptionHelper extends ThrowExceptionHelper {

  private static final String OBJECT_MESSAGE = "Exception when getting the object";
  private static final String LIST_MESSAGE = "Exception when getting the list";

  public static <T> T throwObject(Throwable throwable, Object object) {
    return throwException(throwable, object, new TopSecretException(OBJECT_MESSAGE));
  }

  public static <T> List<T> throwList(Throwable throwable) {
    return throwException(throwable, new TopSecretException(LIST_MESSAGE));
  }
}
