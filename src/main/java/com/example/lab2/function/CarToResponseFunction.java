package com.example.lab2.function;

import com.example.lab2.entities.Car;
import com.example.lab2.dto.GetCarResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarToResponseFunction implements Function<Car, GetCarResponse> {

    @Override
    public GetCarResponse apply(Car entity) {
        return GetCarResponse.builder()
                .id(entity.getCarID())
                .vin(entity.getVin())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .year(entity.getYear())
                .gearbox(entity.getGearbox())
                .build();
    }
}
