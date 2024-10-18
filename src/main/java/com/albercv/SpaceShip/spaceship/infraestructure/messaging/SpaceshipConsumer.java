package com.albercv.SpaceShip.spaceship.infraestructure.messaging;

import com.albercv.SpaceShip.config.RabbitMQConfig;
import com.albercv.SpaceShip.spaceship.application.port.SpaceshipService;
import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipConsumer {

    @Autowired
    private SpaceshipService spaceshipService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(Spaceship spaceship) {
        if (spaceship.getId() == null) {
            spaceshipService.createSpaceship(spaceship);
        } else {
            spaceshipService.updateSpaceship(spaceship.getId(), spaceship);
        }
    }
}
