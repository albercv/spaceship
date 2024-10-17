package com.albercv.SpaceShip.spaceship.domain.port;

import com.albercv.SpaceShip.spaceship.domain.Spaceship;

import java.util.List;
import java.util.Optional;

public interface SpaceshipRepository {
    List<Spaceship> findAll();
    Optional<Spaceship> findById(Long id);
    Spaceship save(Spaceship spaceship);
    void deleteById(Long id);
}
