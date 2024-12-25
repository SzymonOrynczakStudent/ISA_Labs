package com.example.lab2.controller.api;

import com.example.lab2.dto.GetRentalResponse;
import com.example.lab2.dto.GetRentalsResponse;
import com.example.lab2.dto.PutRentalRequest;

import java.util.UUID;

public interface RentalController {

    GetRentalsResponse getRentals();

    GetRentalsResponse getCarRentals(UUID carId);

    GetRentalsResponse getCarRentals(String vin);

    GetRentalResponse getRental(UUID id);

    void putRental(UUID id, PutRentalRequest request);

    void deleteRental(UUID id);

}
