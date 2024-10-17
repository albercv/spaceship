package com.albercv.SpaceShip.spaceship.infraestructure.api;

import com.albercv.SpaceShip.spaceship.application.port.SpaceshipService;
import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipRestController {
    private final SpaceshipService spaceshipService;

    public SpaceshipRestController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }

    @GetMapping
    public ResponseEntity<List<Spaceship>> getAllSpaceships() {
        List<Spaceship> spaceships = spaceshipService.getAllSpaceships();
        return new ResponseEntity<>(spaceships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
        Optional<Spaceship> spaceship = spaceshipService.getSpaceshipById(id);
        return spaceship.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Spaceship> createSpaceship(@RequestBody Spaceship spaceship) {
        Spaceship createdSpaceship = spaceshipService.createSpaceship(spaceship);
        return new ResponseEntity<>(createdSpaceship, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
        try {
            Spaceship updatedSpaceship = spaceshipService.updateSpaceship(id, spaceship);
            return new ResponseEntity<>(updatedSpaceship, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        spaceshipService.deleteSpaceship(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
