package com.dtorres.firequasar.command.infrastructure.event;

import com.dtorres.firequasar.command.infrastructure.configuration.SpaceshipConfigurationProperties;
import com.dtorres.firequasar.command.infrastructure.service.cache.SpaceshipLocationCacheService;
import com.dtorres.firequasar.shared.entity.SpaceshipLocationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FirequasarApplicationEventListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(FirequasarApplicationEventListener.class);

  @Autowired
  private SpaceshipConfigurationProperties spaceshipProperties;

  @Autowired
  private SpaceshipLocationCacheService spaceshipLocationCacheService;

  @EventListener
  public void onApplicationEvent(ContextRefreshedEvent event) {
    LOGGER.info("Verify boot data");

    this.saveBootData(spaceshipProperties.getSpaceships());
  }

  private void saveBootData(List<SpaceshipConfigurationProperties.SpaceshipProperties> spaceships) {
    try {
      spaceshipLocationCacheService.saveAll(spaceships.stream()
                                              .map(spaceship -> new SpaceshipLocationEntity(spaceship.getName(), spaceship.getCoordinateX(), spaceship.getCoordinateY()))
                                              .collect(Collectors.toList()))
                                   .toCompletableFuture()
                                   .get();
    } catch (Exception exception) {
      LOGGER.error(exception.getMessage(), exception);
    }
  }

}
