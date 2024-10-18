package com.albercv.SpaceShip.spaceship.application.port;

import com.albercv.SpaceShip.spaceship.domain.Spaceship;

import java.util.List;
import java.util.Optional;


public interface SpaceshipService {

    List<Spaceship> getAllSpaceships();

    Optional<Spaceship> getSpaceshipById(Long id);

    Spaceship createSpaceship(Spaceship spaceship);

    Spaceship updateSpaceship(Long id, Spaceship spaceship);

    void deleteSpaceship(Long id);
}

