package com.dtorres.firequasar.shared.advice;

import com.dtorres.firequasar.commons.domain.exceptions.ResponseEntityException;
import com.dtorres.firequasar.shared.advice.dto.DtoResponseEntityException;
import org.springframework.http.ResponseEntity;

public abstract class ResponseEntityExceptionAdvice {

  public ResponseEntity<DtoResponseEntityException> processStatus(ResponseEntityException responseEntityException, int codeStatus) {
    return ResponseEntity.status(codeStatus).body(new DtoResponseEntityException(responseEntityException.getMessage()));
  }
}
