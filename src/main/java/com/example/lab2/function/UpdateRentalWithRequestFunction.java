package com.example.lab2.function;

import com.example.lab2.dto.PatchRentalRequest;
import com.example.lab2.entities.Rental;

import java.util.function.BiFunction;

public class UpdateRentalWithRequestFunction implements BiFunction<Rental, PatchRentalRequest, Rental> {

    @Override
    public Rental apply(Rental entity, PatchRentalRequest request) {
        return Rental.builder()
                .rentalID(entity.getRentalID())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .car(entity.getCar())
                .build();
    }
}
