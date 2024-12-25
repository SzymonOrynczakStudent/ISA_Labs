/*package com.example.lab2.service.impl;

//TODO: najpierw creaty, potem inicjalizowanie, rozbudowanie serwisów (więcej opcji typu usuwanie, updaty, listowanie)

import com.example.lab2.entities.Car;
import com.example.lab2.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarServiceSuggestion {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceSuggestion(CarRepository carRepository) { this.carRepository = carRepository; }

    public Car createCar(String vin, String brand, String model, int year, String gearbox) {
        Car newCar = Car.builder()
                .carID(UUID.randomUUID())
                .vin(vin)
                .brand(brand)
                .model(model)
                .year(year)
                .gearbox(gearbox)
                .build();

        carRepository.save(newCar);

        return newCar;
    }
}*/
