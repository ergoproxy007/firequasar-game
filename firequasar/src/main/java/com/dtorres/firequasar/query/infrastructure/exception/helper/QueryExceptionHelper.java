package com.dtorres.firequasar.query.infrastructure.exception.helper;

import java.util.List;

import com.dtorres.firequasar.commons.domain.exceptions.NotFoundDataException;
import com.dtorres.firequasar.commons.helper.ThrowExceptionHelper;

public class QueryExceptionHelper extends ThrowExceptionHelper {

  private static final String NOT_ENOUGHT_MESSAGE = "Not enough information";

  private static NotFoundDataException getNotFoundDataException(Throwable throwable) {
    if(throwable instanceof NotFoundDataException) {
      return (NotFoundDataException) throwable;
    }
    if(throwable.getCause() instanceof NotFoundDataException) {
      return (NotFoundDataException) throwable.getCause();
    }
    return new NotFoundDataException(NOT_ENOUGHT_MESSAGE);
  }

  public static <T> T throwObject(Throwable throwable, Object object) {
    return throwException(throwable, object, getNotFoundDataException(throwable));
  }

  public static <T> List<T> throwList(Throwable throwable) {
    return throwException(throwable, getNotFoundDataException(throwable));
  }
}
