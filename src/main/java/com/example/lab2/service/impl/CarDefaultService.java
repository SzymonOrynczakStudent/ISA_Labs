package com.example.lab2.service.impl;

import com.example.lab2.entities.Car;
import com.example.lab2.repository.CarRepository;
import com.example.lab2.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarDefaultService implements CarService {

    private final CarRepository repository;

    @Autowired
    public CarDefaultService(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Car> find(UUID carId) {
        return repository.findById(carId);
    }

    @Override
    public Optional<Car> find(String vin) { return repository.findByVin(vin); }

    @Override
    public void create(Car car) {
        repository.save(car);
    }

    @Override
    public void update(Car car) {
        repository.save(car);
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }
}
