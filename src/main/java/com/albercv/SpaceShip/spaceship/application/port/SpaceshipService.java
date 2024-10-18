package com.albercv.SpaceShip.spaceship.application.port;

import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface SpaceshipService {

    Page<Spaceship> getAllSpaceships(Pageable pageable);

    Optional<Spaceship> getSpaceshipById(Long id);

    Spaceship createSpaceship(Spaceship spaceship);

    Spaceship updateSpaceship(Long id, Spaceship spaceship);

    void deleteSpaceship(Long id);
}

