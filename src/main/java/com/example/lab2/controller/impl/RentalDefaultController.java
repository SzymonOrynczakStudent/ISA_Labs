package com.example.lab2.controller.impl;

import com.example.lab2.controller.api.RentalController;
import com.example.lab2.dto.GetRentalResponse;
import com.example.lab2.dto.GetRentalsResponse;
import com.example.lab2.dto.PatchRentalRequest;
import com.example.lab2.dto.PutRentalRequest;
import com.example.lab2.function.RentalToResponseFunction;
import com.example.lab2.function.RentalsToResponseFunction;
import com.example.lab2.function.RequestToRentalFunction;
import com.example.lab2.function.UpdateRentalWithRequestFunction;
import com.example.lab2.service.api.RentalService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
public class RentalDefaultController implements RentalController {

    private final RentalService service;

    private final RentalToResponseFunction rentalToResponse;

    private final RentalsToResponseFunction rentalsToResponse;

    private final RequestToRentalFunction requestToRental;

    private final UpdateRentalWithRequestFunction updateRentalRequest;


    @Autowired
    public RentalDefaultController(
            RentalService service,
            RentalToResponseFunction rentalToResponse,
            RentalsToResponseFunction rentalsToResponse,
            RequestToRentalFunction requestToRentalFunction,
            UpdateRentalWithRequestFunction updateRentalRequest
    ) {
        this.service = service;
        this.rentalToResponse = rentalToResponse;
        this.rentalsToResponse = rentalsToResponse;
        this.requestToRental = requestToRentalFunction;
        this.updateRentalRequest = updateRentalRequest;
    }

    @Override
    public GetRentalsResponse getRentals() {
        return rentalsToResponse.apply(service.findAll());
    }

    @Override
    public GetRentalsResponse getCarRentals(UUID carId) {
        return service.findAllByCar(carId)
                .map(rentalsToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetRentalResponse getRental(UUID id) {
        return service.find(id)
                .map(rentalToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void updateRental(UUID id, PatchRentalRequest request) {
        service.update(updateRentalRequest.apply(id, request));
    }

    @Override
    public void putRental(UUID id, PutRentalRequest request) {
        service.create(requestToRental.apply(id, request));
    }

    @Override
    public void deleteRental(UUID id) {
        service.find(id)
                .ifPresentOrElse(
                    rental -> service.delete(id),
                    () -> {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
        );


    }
}
