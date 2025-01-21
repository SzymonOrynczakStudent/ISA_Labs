package org.example.function;

import org.example.dto.GetCarResponse;
import org.example.entity.Car;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarToResponseFunction implements Function<Car, GetCarResponse> {

    @Override
    public GetCarResponse apply(Car entity) {

        return GetCarResponse.builder()
                .id(entity.getCarId())
                .vin(entity.getVin())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .year(entity.getYear())
                .gearbox(entity.getGearbox())
                .build();
    }
}