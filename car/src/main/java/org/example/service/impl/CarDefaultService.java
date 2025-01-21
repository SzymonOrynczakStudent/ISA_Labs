package org.example.service.impl;

import org.example.entity.Car;
import org.example.event.repository.api.CarEventRepository;
import org.example.repository.CarRepository;
import org.example.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarDefaultService implements CarService {

    private final CarRepository repository;

    private final CarEventRepository eventRepository;

    @Autowired
    public CarDefaultService(CarRepository repository, CarEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Car> find(UUID carId) {
        return repository.findById(carId);
    }

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
        eventRepository.delete(id);
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }
}
