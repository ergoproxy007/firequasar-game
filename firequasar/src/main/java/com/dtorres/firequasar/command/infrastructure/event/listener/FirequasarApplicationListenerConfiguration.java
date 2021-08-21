package com.dtorres.firequasar.command.infrastructure.event.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class FirequasarListenerConfiguration {

  @EventListener
  public void onApplicationEvent(MyCustomApplicationEvent event) {
    System.out.println("MyCustomApplicationListenerConfiguration      : Source : "+event.getSource().getClass().getName() + ", Message : "+ event.getName());
  }
}
