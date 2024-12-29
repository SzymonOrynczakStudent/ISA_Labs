package com.example.lab2.function;

import com.example.lab2.dto.PatchCarRequest;
import com.example.lab2.entities.Car;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class UpdateCarWithRequestFunction implements BiFunction<UUID, PatchCarRequest, Car> {

    @Override
    public Car apply(UUID id, PatchCarRequest request) {
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
