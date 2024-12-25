package com.example.lab2.controller.impl;

import com.example.lab2.dto.GetCarsResponse;
import com.example.lab2.controller.api.CarController;
import com.example.lab2.function.*;
import com.example.lab2.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CarDefaultController implements CarController {

    private final CarService service;

    private final CarToResponseFunction carToResponse;

    private final CarsToResponseFunction carsToResponse;

    @Autowired
    public CarDefaultController(
            CarService service,
            CarToResponseFunction carToResponse,
            CarsToResponseFunction carsToResponse
    ) {
        this.service = service;
        this.carToResponse = carToResponse;
        this.carsToResponse = carsToResponse;
    }

    @Override
    public GetCarsResponse getCars() {
        return carsToResponse.apply(service.findAll());
    }

}
