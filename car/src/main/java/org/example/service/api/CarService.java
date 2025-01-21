package org.example.service.api;

import org.example.entity.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarService {

    Optional<Car> find(UUID carId);
    List<Car> findAll();
    void create(Car car);
    void update(Car car);
    void delete(UUID id);

}
