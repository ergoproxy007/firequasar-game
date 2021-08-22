package com.dtorres.firequasar.common;

import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.fail;

public class CommonUnitTest {

  protected static <T> T getResultPromise(CompletionStage<T> promise) {
    T result = null;
    try {
      result = promise.toCompletableFuture().get();
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    return result;
  }
}
