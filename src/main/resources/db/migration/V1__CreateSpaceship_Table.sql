CREATE TABLE spaceship (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           model VARCHAR(255),
                           manufacturer VARCHAR(255),
                           crewCapacity INT,
                           passengerCapacity INT
);
