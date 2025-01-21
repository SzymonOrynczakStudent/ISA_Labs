package org.example.function;

import org.example.dto.GetCarsResponse;
import org.example.entity.Car;
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
                                .vin(car.getVin())
                                .brand(car.getBrand())
                                .model(car.getModel())
                                .year(car.getYear())
                                .gearbox(car.getGearbox())
                                .build())
                        .toList())
                .build();
    }
}