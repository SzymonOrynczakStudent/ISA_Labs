package com.example.lab2.function;

import com.example.lab2.dto.GetCarsResponse;
import com.example.lab2.entities.Car;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class CarsToResponseFunction implements Function<List<Car>, GetCarsResponse> {

    @Override
    public GetCarsResponse apply(List<Car> entities) {
        return GetCarsResponse.builder()
                .cars(entities.stream()
                        .map(car -> GetCarsResponse.Car.builder()
                                .id(car.getCarId())
                                .carDetails(String.format("%s %s, %s gearbox",
                                        car.getBrand(), car.getModel(), car.getGearbox()))
                                .build())
                        .toList())
                .build();
    }
}
