package com.example.lab2.controller.api;

import com.example.lab2.dto.GetRentalResponse;
import com.example.lab2.dto.GetRentalsResponse;
import com.example.lab2.dto.PatchRentalRequest;
import com.example.lab2.dto.PutRentalRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface RentalController {

    @GetMapping("api/rentals")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetRentalsResponse getRentals();

    @GetMapping("/api/cars/{carId}/rentals")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetRentalsResponse getCarRentals(
            @PathVariable("carId")
            UUID carId
    );

    @GetMapping("/api/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetRentalResponse getRental(
            @PathVariable("id")
            UUID id
    );

    @PatchMapping("/api/rentals/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateRental(
            @PathVariable("id") UUID id,
            @RequestBody PatchRentalRequest request
    );

    @PutMapping("/api/rentals/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putRental(
            @PathVariable("id") UUID id,
            @RequestBody PutRentalRequest request
    );

    @DeleteMapping("/api/rentals/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRental(
            @PathVariable("id")
            UUID id
    );

}
