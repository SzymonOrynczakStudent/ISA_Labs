package com.example.lab2.controller.impl;

import com.example.lab2.dto.GetCarResponse;
import com.example.lab2.dto.GetCarsResponse;
import com.example.lab2.controller.api.CarController;
import com.example.lab2.dto.PatchCarRequest;
import com.example.lab2.dto.PutCarRequest;
import com.example.lab2.function.*;
import com.example.lab2.service.api.CarService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log

public class CarDefaultController implements CarController {

    private final CarService service;

    private final CarToResponseFunction carToResponse;

    private final CarsToResponseFunction carsToResponse;

    private final RequestToCarFunction requestToCar;

    private final UpdateCarWithRequestFunction updateCarRequest;

    @Autowired
    public CarDefaultController(
            CarService service,
            CarToResponseFunction carToResponse,
            CarsToResponseFunction carsToResponse,
            RequestToCarFunction requestToCar,
            UpdateCarWithRequestFunction updateCarRequest
    ) {
        this.service = service;
        this.carToResponse = carToResponse;
        this.carsToResponse = carsToResponse;
        this.requestToCar = requestToCar;
        this.updateCarRequest = updateCarRequest;
    }

    @Override
    public GetCarsResponse getCars() {
        return carsToResponse.apply(service.findAll());
    }

    @Override
    public GetCarResponse getCar(UUID id) {
        return service.find(id)
                .map(carToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void updateCar(UUID id, PatchCarRequest request) {
        service.update(updateCarRequest.apply(id, request));
    }

    @Override
    public void deleteCar(UUID id) {
        service.delete(id);
    }

    @Override
    public void putCar(UUID id, PutCarRequest request) {
        service.create(requestToCar.apply(id, request));
    }

}
