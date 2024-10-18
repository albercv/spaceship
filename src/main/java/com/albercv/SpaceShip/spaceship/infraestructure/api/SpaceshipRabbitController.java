package com.albercv.SpaceShip.spaceship.infraestructure.api;

import com.albercv.SpaceShip.config.RabbitMQConfig;
import com.albercv.SpaceShip.spaceship.domain.Spaceship;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/rabbit")
@Controller
public class SpaceshipRabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/form")
    public String spaceshipForm(Model model) {
        model.addAttribute("spaceship", new Spaceship());
        return "spaceshipForm";
    }

    @PostMapping("/sendSpaceship")
    public String sendSpaceship(@ModelAttribute Spaceship spaceship) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, spaceship);
        return "redirect:/api/rabbit/form";
    }
}

