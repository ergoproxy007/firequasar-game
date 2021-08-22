package com.dtorres.firequasar.command.domain.service;

import com.dtorres.firequasar.command.domain.model.Position;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.common.CommonUnitTest;
import com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static com.dtorres.firequasar.testdatabuilder.domain.TestDataBuilderSpaceship.*;
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
    CompletionStage<String> messagePromise = this.messageService.getMessage(this.getMiltidimiensional());
    String message = getResultPromise(messagePromise);
    //Assert
    assertEquals(messagePredicted, message, "message is decoded");
  }

  private String[][] getMiltidimiensional() {
    String[] kenobi = {"este", "", "", "mensaje", ""};
    String[] skywalker = {"", "es", "", "", "secreto"};
    String[] sato = {"este", "", "un", "", "",""};
    return Arrays.asList(kenobi, skywalker, sato).stream().toArray(String[][]::new);
  }
}
