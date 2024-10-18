package com.albercv.SpaceShip.spaceship.application;

import com.albercv.SpaceShip.spaceship.application.port.SpaceshipService;
import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import com.albercv.SpaceShip.spaceship.domain.port.SpaceshipRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {
    private final SpaceshipRepository spaceshipRepository;

    public SpaceshipServiceImpl(SpaceshipRepository spaceshipRepository) {
        this.spaceshipRepository = spaceshipRepository;
    }

    @Cacheable(value = "spaceships", key = "#root.args[0]")
    @Override
    public Page<Spaceship> getAllSpaceships(Optional<String> name, Pageable pageable) {
        return name.map(n -> spaceshipRepository.findByNameContainingIgnoreCase(n, pageable))
                .orElseGet(() -> spaceshipRepository.findAll(pageable));
    }

    @Cacheable(value = "spaceships", key = "#id")
    @Override
    public Optional<Spaceship> getSpaceshipById(Long id) {
        return spaceshipRepository.findById(id);
    }

    @CacheEvict(value = "spaceships", allEntries = true)
    @Override
    public Spaceship createSpaceship(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    @CacheEvict(value = "spaceships", allEntries = true)
    @Override
    public Spaceship updateSpaceship(Long id, Spaceship spaceship) {
        if (spaceshipRepository.findById(id).isPresent()) {
            spaceship.setId(id);
            return spaceshipRepository.save(spaceship);
        } else {
            throw new RuntimeException("Spaceship not found");
        }
    }

    @CacheEvict(value = "spaceships", allEntries = true)
    @Override
    public void deleteSpaceship(Long id) {
        spaceshipRepository.deleteById(id);
    }
}
