package com.dtorres.firequasar.command.application.factory;

import com.dtorres.firequasar.command.application.command.SatelliteCommand;
import com.dtorres.firequasar.command.domain.model.Spaceship;
import com.dtorres.firequasar.commons.domain.exceptions.NotFoundDataException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpaceshipFactory {

  public List<Spaceship> convertToListBySatelliteCommand(List<SatelliteCommand> satellites) {
    if(satellites == null) {
      throw new NotFoundDataException();
    }
    return satellites.stream().map(this::convertBySatelliteCommand).collect(Collectors.toList());
  }

  public Spaceship convertBySatelliteCommand(SatelliteCommand satelliteCommand) {
    return Spaceship.create(satelliteCommand.getName(), satelliteCommand.getDistance(), satelliteCommand.getMessage());
  }

}
