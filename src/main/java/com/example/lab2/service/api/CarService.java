package com.example.lab2.service.api;

import com.example.lab2.entities.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarService {

    Optional<Car> find(UUID carId);
    Optional<Car> find(String vin);
    List<Car> findAll();
    void create(Car car);
    void update(Car car);
    void delete(UUID id);

}
