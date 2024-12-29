package com.example.lab2.function;

import com.example.lab2.entities.Car;
import com.example.lab2.dto.GetCarResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarToResponseFunction implements Function<Car, GetCarResponse> {

    @Override
    public GetCarResponse apply(Car entity) {
        String carDetails = String.format("%s %s, %s gearbox", entity.getBrand(), entity.getModel(), entity.getGearbox());

        return GetCarResponse.builder()
                .id(entity.getCarId())
                .vin(entity.getVin())
                .year(entity.getYear())
                .carDetails(carDetails)
                .build();
    }
}
