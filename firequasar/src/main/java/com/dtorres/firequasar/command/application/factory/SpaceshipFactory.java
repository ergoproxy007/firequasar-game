package com.dtorres.firequasar.command.application.factory;

import com.dtorres.firequasar.command.application.command.SatelliteCommand;
import com.dtorres.firequasar.command.application.command.SatelliteCommandConsolidated;
import com.dtorres.firequasar.command.domain.exception.NotFoundDataException;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpaceshipFactory {

  public List<Spaceship> convertToList(List<SatelliteCommand> satellites) {
    if(satellites == null) {
      throw new NotFoundDataException();
    }
    return satellites.stream().map(this::convert).collect(Collectors.toList());
  }

  public Spaceship convert(SatelliteCommand satelliteCommand) {
    return Spaceship.create(satelliteCommand.getName(), satelliteCommand.getDistance(), satelliteCommand.getMessages());
  }
}
