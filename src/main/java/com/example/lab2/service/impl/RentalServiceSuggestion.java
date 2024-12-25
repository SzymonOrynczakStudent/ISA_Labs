/*
package com.example.lab2.service.impl;

import com.example.lab2.entities.Car;
import com.example.lab2.entities.Rental;
import com.example.lab2.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RentalServiceSuggestion {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalServiceSuggestion(RentalRepository rentalRepository) { this.rentalRepository = rentalRepository; }

    public Rental createRental(LocalDate startDate, LocalDate endDate, Car car) {
        Rental newRental = Rental.builder()
                .rentalID(UUID.randomUUID())
                .startDate(startDate)
                .endDate(endDate)
                .car(car)
                .build();

        rentalRepository.save(newRental);

        return newRental;
    }
}*/
