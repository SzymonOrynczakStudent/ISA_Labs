package org.example.event.repository.rest;

import org.example.entity.Car;
import org.example.event.repository.api.CarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class CarEventRestRepository implements CarEventRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public CarEventRestRepository(RestTemplate template) {
        this.restTemplate = template;
    }

    @Override
    public void create(Car car) {
        restTemplate.put("/api/cars/{id}", car);
    }

    @Override
    public void update(UUID id, Car car) {
        restTemplate.patchForObject("/api/cars/{id}", car, Void.class); //or String.class maybe?
    }

    @Override
    public void delete(UUID id) {
        restTemplate.delete("/api/cars/{id}", id);
    }
}
