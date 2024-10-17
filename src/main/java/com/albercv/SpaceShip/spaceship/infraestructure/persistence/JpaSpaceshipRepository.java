package com.albercv.SpaceShip.spaceship.infraestructure.persistence;

import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import com.albercv.SpaceShip.spaceship.domain.port.SpaceshipRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSpaceshipRepository extends JpaRepository<Spaceship, Long>, SpaceshipRepository {
}
