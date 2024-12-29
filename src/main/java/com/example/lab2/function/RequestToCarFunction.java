package com.example.lab2.function;

import com.example.lab2.dto.PutCarRequest;
import com.example.lab2.entities.Car;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToCarFunction implements BiFunction<UUID, PutCarRequest, Car> {

    @Override
    public Car apply(UUID id, PutCarRequest request) {
        return Car.builder()
                .carId(id)
                .vin(request.getVin())
                .brand(request.getBrand())
                .model(request.getModel())
                .year(request.getYear())
                .gearbox(request.getGearbox())
                .build();
    }
}
