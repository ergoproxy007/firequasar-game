package com.dtorres.firequasar.command.domain.service;

import static com.dtorres.firequasar.helper.MultiMessageHelper.getMiltidimiensional;

import com.dtorres.firequasar.common.CommonUnitTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MessageServiceTest extends CommonUnitTest {

  @InjectMocks
  private MessageService messageService;

  @Test
  public void getMessageTest() {
    //Arrange
    String messagePredicted = "este es un mensaje secreto";
    //Act
    CompletionStage<String> messagePromise = this.messageService.getMessage(getMiltidimiensional());
    String messageResult = getResultPromise(messagePromise);
    //Assert
    assertEquals(messagePredicted, messageResult, "message is decoded");
  }

}
