package com.example.lab2.function;

import com.example.lab2.dto.GetRentalsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import com.example.lab2.entities.Rental;

@Component
public class RentalsToResponseFunction implements Function<List<Rental>, GetRentalsResponse> {

    @Override
    public GetRentalsResponse apply(List<Rental> entities) {
        return GetRentalsResponse.builder()
                .rentals(entities.stream()
                        .map(rental -> GetRentalsResponse.Rental.builder()
                                .id(rental.getRentalId())
                                .startDate(rental.getStartDate().toString())
                                .endDate(rental.getEndDate().toString())
                                .carDetails(String.format("%s %s, %s gearbox",
                                        rental.getCar().getBrand(),
                                        rental.getCar().getModel(),
                                        rental.getCar().getGearbox()))
                                .build())
                        .toList())
                .build();
    }
}

