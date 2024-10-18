package com.albercv.SpaceShip.config;

import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import com.albercv.SpaceShip.spaceship.domain.port.SpaceshipRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(SpaceshipRepository spaceshipRepository) {
        return args -> {
            if (spaceshipRepository.count() == 0) {
                spaceshipRepository.saveAll(List.of(
                        new Spaceship(10L, "Spaceship Alpha", "Type A", "2024LM", 12, 54),
                        new Spaceship(11L, "Spaceship Beta", "Type B", "2023GP", 9, 400),
                        new Spaceship(12L, "Spaceship Gamma", "Type C", "2022HG", 120, 6000)
                ));
            }
        };
    }
}

