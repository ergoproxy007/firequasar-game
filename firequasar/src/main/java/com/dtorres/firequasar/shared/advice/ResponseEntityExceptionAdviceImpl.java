package com.dtorres.firequasar.shared.advice;

import com.dtorres.firequasar.commons.domain.exceptions.NotAcceptedAttributeException;
import com.dtorres.firequasar.commons.domain.exceptions.NotFoundDataException;
import com.dtorres.firequasar.commons.domain.exceptions.TopSecretException;
import com.dtorres.firequasar.shared.advice.dto.DtoResponseEntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionAdviceImpl extends ResponseEntityExceptionAdvice {

  private static final int STATUS_404 = 404;

  @ExceptionHandler(TopSecretException.class)
  public ResponseEntity<DtoResponseEntityException> processStatus404ForTopSecretException(TopSecretException exception) {
    return processStatus(exception, STATUS_404);
  }

  @ExceptionHandler(NotFoundDataException.class)
  public ResponseEntity<DtoResponseEntityException> processStatus404ForNotFoundDataException(NotFoundDataException exception) {
    return processStatus(exception, STATUS_404);
  }

  @ExceptionHandler(NotAcceptedAttributeException.class)
  public ResponseEntity<DtoResponseEntityException> processStatus404ForNotAcceptedAttributeException(NotAcceptedAttributeException exception) {
    return processStatus(exception, STATUS_404);
  }
}
