package com.dtorres.firequasar.shared.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dtorres.firequasar.commons.domain.exceptions.NotAcceptedAttributeException;
import com.dtorres.firequasar.commons.domain.exceptions.NotFoundDataException;
import com.dtorres.firequasar.commons.domain.exceptions.TopSecretException;
import com.dtorres.firequasar.shared.advice.dto.DtoResponseEntityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ResponseExceptionAdviceImplTest {

  private static final String MESSAGE_ERROR = "This is an error";
  private static final int STATUS_404 = 404;

  @InjectMocks
  ResponseEntityExceptionAdviceImpl responseExceptionAdviceImpl;

  @Test
  public void processStatus404ForTopSecretExceptionTest() {
    ResponseEntity<DtoResponseEntityException> exception = responseExceptionAdviceImpl.processStatus404ForTopSecretException(new TopSecretException(MESSAGE_ERROR));
    assertEquals(STATUS_404, exception.getStatusCodeValue());
  }

  @Test
  public void processStatus404ForNotFoundDataExceptionTest() {
    ResponseEntity<DtoResponseEntityException> exception = responseExceptionAdviceImpl.processStatus404ForNotFoundDataException(new NotFoundDataException(MESSAGE_ERROR));
    assertEquals(STATUS_404, exception.getStatusCodeValue());
  }

  @Test
  public void processStatus404ForNotAcceptedAttributeExceptionTest() {
    ResponseEntity<DtoResponseEntityException> exception = responseExceptionAdviceImpl.processStatus404ForNotAcceptedAttributeException(new NotAcceptedAttributeException(MESSAGE_ERROR));
    assertEquals(STATUS_404, exception.getStatusCodeValue());
  }
}
