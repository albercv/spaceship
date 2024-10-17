package com.albercv.SpaceShip.spaceship.application;

import com.albercv.SpaceShip.spaceship.application.port.SpaceshipService;
import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import com.albercv.SpaceShip.spaceship.domain.port.SpaceshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {
    private final SpaceshipRepository spaceshipRepository;

    public SpaceshipServiceImpl(SpaceshipRepository spaceshipRepository) {
        this.spaceshipRepository = spaceshipRepository;
    }

    @Override
    public List<Spaceship> getAllSpaceships() {
        return spaceshipRepository.findAll();
    }

    @Override
    public Optional<Spaceship> getSpaceshipById(Long id) {
        return spaceshipRepository.findById(id);
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    @Override
    public Spaceship updateSpaceship(Long id, Spaceship spaceship) {
        if (spaceshipRepository.findById(id).isPresent()) {
            spaceship.setId(id);
            return spaceshipRepository.save(spaceship);
        } else {
            throw new RuntimeException("Spaceship not found");
        }
    }

    @Override
    public void deleteSpaceship(Long id) {
        spaceshipRepository.deleteById(id);
    }
}
