package com.dtorres.firequasar.command.domain.service;

import static com.dtorres.firequasar.shared.constants.ConstantsText.EMPTY;
import static com.dtorres.firequasar.shared.constants.ConstantsText.SPACE;
import static com.dtorres.firequasar.shared.constants.ConstantsNumber.ZERO;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.concurrent.CompletionStage;

@Service
public class MessageService {

  public CompletionStage<String> getMessage(String[]... messages) {
    return supplyAsync(() -> this.decodeMessage(messages));
  }

  private String decodeMessage(String[]... messages) {
    Map<Integer, List<String>> mapMessagesByIndex = new HashMap<>();
    Arrays.stream(messages).filter(msgArray -> msgArray != null && msgArray.length > ZERO)
                           .forEach(msgArray -> this.populateListInMapByIndex(Arrays.asList(msgArray), mapMessagesByIndex));
    return this.joinMessage(mapMessagesByIndex);
  }

  private void populateListInMapByIndex(List<String> msgArray, Map<Integer, List<String>> mapByIndex) {
    msgArray.forEach(message -> {
              Integer index = msgArray.indexOf(message);
              if(!mapByIndex.containsKey(index)) {
                mapByIndex.put(index, new ArrayList<>());
              }
              List<String> risingList = mapByIndex.get(index);
              risingList.add(message);
             });
  }

  private String joinMessage(Map<Integer, List<String>> mapMessagesByIndex) {
    StringBuilder messageConsolidate = new StringBuilder();
    mapMessagesByIndex.forEach((index, messages) -> {
                        String uniqueMessage = messages.stream().filter(message -> !EMPTY.equals(message.trim())).findFirst().orElse(EMPTY);
                        messageConsolidate.append(uniqueMessage);
                        messageConsolidate.append(SPACE);
    });
    return messageConsolidate.toString().trim();
  }
}
