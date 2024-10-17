package com.albercv.SpaceShip.spaceship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spaceship {
    private Long id;
    private String name;
    private String model;
    private String manufacturer;
    private int crewCapacity;
    private int passengerCapacity;
}
