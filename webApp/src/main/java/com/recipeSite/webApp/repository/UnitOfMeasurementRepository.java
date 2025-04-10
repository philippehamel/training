package com.recipeSite.webApp.repository;

import com.recipeSite.webApp.model.UnitOfMeasurement;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long> {
    Optional<UnitOfMeasurement> findByDescription(String description);
}
