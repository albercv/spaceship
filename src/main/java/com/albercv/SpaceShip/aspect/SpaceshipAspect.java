package com.albercv.SpaceShip.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpaceshipAspect {

    private static final Logger logger = LoggerFactory.getLogger(SpaceshipAspect.class);

    @Before("execution(* com.albercv.SpaceShip.spaceship.application.port.SpaceshipService.getSpaceshipById(..)) && args(id)")
    public void logIfNegativeId(JoinPoint joinPoint, Long id) {
        if (id < 0) {
            logger.warn("Attempt to request a spaceship with a negative ID: " + id);
        }
    }
}

