package org.example.event.repository.api;

import org.example.entity.Car;

import java.util.UUID;

public interface CarEventRepository {

    void create(Car car);
    void update(UUID id, Car car);
    void delete(UUID id);
}
