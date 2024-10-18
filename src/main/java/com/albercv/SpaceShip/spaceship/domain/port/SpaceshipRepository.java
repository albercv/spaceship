package com.albercv.SpaceShip.spaceship.domain.port;

import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
}
