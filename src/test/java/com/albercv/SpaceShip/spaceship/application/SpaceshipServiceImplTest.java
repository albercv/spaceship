package com.albercv.SpaceShip.spaceship.application;

import static org.junit.jupiter.api.Assertions.*;

import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import com.albercv.SpaceShip.spaceship.domain.port.SpaceshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpaceshipServiceImplTest {

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipServiceImpl spaceshipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSpaceships() {

        Spaceship spaceship1 = new Spaceship(1L, "X-Wing", "T-65", "Incom Corporation", 1, 0);
        Spaceship spaceship2 = new Spaceship(2L, "Millennium Falcon", "YT-1300", "Corellian Engineering", 4, 6);

        List<Spaceship> spaceshipList = Arrays.asList(spaceship1, spaceship2);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Spaceship> page = new PageImpl<>(spaceshipList, pageable, spaceshipList.size());


        when(spaceshipRepository.findAll(pageable)).thenReturn(page);

        Page<Spaceship> result = spaceshipService.getAllSpaceships(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals("X-Wing", result.getContent().getFirst().getName());
        verify(spaceshipRepository, times(1)).findAll(pageable);
    }

    @Test
    void getSpaceshipById() {
        Spaceship spaceship = new Spaceship(1L, "X-Wing", "T-65", "Incom Corporation", 1, 0);

        when(spaceshipRepository.findById(1L)).thenReturn(Optional.of(spaceship));

        Optional<Spaceship> result = spaceshipService.getSpaceshipById(1L);

        assertTrue(result.isPresent());
        assertEquals("X-Wing", result.get().getName());
        verify(spaceshipRepository, times(1)).findById(1L);
    }

    @Test
    void createSpaceship() {
        Spaceship spaceship = new Spaceship(null, "X-Wing", "T-65", "Incom Corporation", 1, 0);

        when(spaceshipRepository.save(any(Spaceship.class))).thenReturn(spaceship);

        Spaceship result = spaceshipService.createSpaceship(spaceship);

        assertNotNull(result);
        verify(spaceshipRepository, times(1)).save(spaceship);
    }

    @Test
    void deleteSpaceship() {
        spaceshipService.deleteSpaceship(1L);

        verify(spaceshipRepository, times(1)).deleteById(1L);
    }
}
