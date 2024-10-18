package com.albercv.SpaceShip.spaceship.infraestructure.api;

import com.albercv.SpaceShip.exception.CustomNotFoundException;
import com.albercv.SpaceShip.spaceship.application.port.SpaceshipService;
import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spaceships")
@Tag(name = "Spaceships API", description = "Manage spaceships")
public class SpaceshipRestController {
    private final SpaceshipService spaceshipService;

    public SpaceshipRestController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }

    @GetMapping
    public ResponseEntity<Page<Spaceship>> getAllSpaceships(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));

        Page<Spaceship> spaceships = spaceshipService.getAllSpaceships(Optional.ofNullable(name), pageable);
        return new ResponseEntity<>(spaceships, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get spaceship by ID", description = "Returns spaceship details for the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship found"),
            @ApiResponse(responseCode = "404", description = "Spaceship not found")
    })
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
        Optional<Spaceship> spaceship = spaceshipService.getSpaceshipById(id);
        return spaceship
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseThrow(() -> new CustomNotFoundException("Spaceship with ID " + id + " not found."));
    }

    @PostMapping
    @Operation(summary = "Create a new spaceship", description = "Adds a new spaceship to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Spaceship created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Spaceship> createSpaceship(@RequestBody Spaceship spaceship) {
        Spaceship createdSpaceship = spaceshipService.createSpaceship(spaceship);
        return new ResponseEntity<>(createdSpaceship, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing spaceship", description = "Updates the details of an existing spaceship by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spaceship updated successfully"),
            @ApiResponse(responseCode = "404", description = "Spaceship not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
        try {
            Spaceship updatedSpaceship = spaceshipService.updateSpaceship(id, spaceship);
            return new ResponseEntity<>(updatedSpaceship, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a spaceship", description = "Deletes an existing spaceship by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Spaceship deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Spaceship not found")
    })
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        spaceshipService.deleteSpaceship(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
