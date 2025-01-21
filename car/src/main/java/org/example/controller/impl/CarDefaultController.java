package org.example.controller.impl;

import lombok.extern.java.Log;
import org.example.controller.api.CarController;
import org.example.dto.GetCarResponse;
import org.example.dto.GetCarsResponse;
import org.example.dto.PatchCarRequest;
import org.example.dto.PutCarRequest;
import org.example.function.CarToResponseFunction;
import org.example.function.CarsToResponseFunction;
import org.example.function.UpdateCarWithRequestFunction;
import org.example.function.RequestToCarFunction;
import org.example.service.api.CarService;
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

    // It is removing a car properly but there is an error in response
    @Override
    public void deleteCar(UUID id) {
        //service.delete(id);
        service.find(id)
                .ifPresentOrElse(
                        car -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

    @Override
    public void updateCar(UUID id, PatchCarRequest request) {
        service.update(updateCarRequest.apply(id, request));
    }

    @Override
    public void putCar(UUID id, PutCarRequest request) {
        service.create(requestToCar.apply(id, request));
    }
}

