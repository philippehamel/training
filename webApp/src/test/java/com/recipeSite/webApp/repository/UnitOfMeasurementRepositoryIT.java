package com.recipeSite.webApp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipeSite.webApp.model.UnitOfMeasurement;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UnitOfMeasurementRepositoryIT {

    @Autowired
    UnitOfMeasurementRepository unitOfMeasurementRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasurement> unitOfMeasurement = unitOfMeasurementRepository
                .findByDescription("Teaspoon");
        assertEquals("Teaspoon", unitOfMeasurement.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasurement> unitOfMeasurement = unitOfMeasurementRepository
                .findByDescription("Cup");
        assertEquals("Cup", unitOfMeasurement.get().getDescription());
    }
}
