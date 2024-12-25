package com.example.lab2.controller.impl;

import com.example.lab2.controller.api.RentalController;
import com.example.lab2.dto.GetRentalResponse;
import com.example.lab2.dto.GetRentalsResponse;
import com.example.lab2.dto.PutRentalRequest;
import com.example.lab2.function.RentalToResponseFunction;
import com.example.lab2.function.RentalsToResponseFunction;
import com.example.lab2.function.RequestToRentalFunction;
import com.example.lab2.service.api.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
public class RentalDefaultController implements RentalController {

    private final RentalService service;

    private final RentalToResponseFunction rentalToResponse;

    private final RentalsToResponseFunction rentalsToResponse;

    private final RequestToRentalFunction requestToRental;

    @Autowired
    public RentalDefaultController(
            RentalService service,
            RentalToResponseFunction rentalToResponse,
            RentalsToResponseFunction rentalsToResponse,
            RequestToRentalFunction requestToRentalFunction
    ) {
        this.service = service;
        this.rentalToResponse = rentalToResponse;
        this.rentalsToResponse = rentalsToResponse;
        this.requestToRental = requestToRentalFunction;
    }

    @Override
    public GetRentalsResponse getRentals() {
        return rentalsToResponse.apply(service.findAll());
    }

    @Override
    public GetRentalsResponse getCarRentals(UUID carId) {
        return service.findAllByCar(carId)
                .map(rentalsToResponse)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public GetRentalsResponse getCarRentals(String vin) {
        return service.findAllByCar(vin)
                .map(rentalsToResponse)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public GetRentalResponse getRental(UUID id) {
        return service.find(id)
                .map(rentalToResponse)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void putRental(UUID id, PutRentalRequest request) {
        service.create(requestToRental.apply(id, request));
    }

    @Override
    public void deleteRental(UUID id) {
        service.delete(id);
    }
}
