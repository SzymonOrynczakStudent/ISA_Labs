package com.example.lab2.function;

import com.example.lab2.dto.GetRentalResponse;
import com.example.lab2.entities.Rental;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RentalToResponseFunction implements Function<Rental, GetRentalResponse> {


    @Override
    public GetRentalResponse apply(Rental entity) {
        return GetRentalResponse.builder()
                .id(entity.getRentalID())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .car(GetRentalResponse.Car.builder()
                        .id(entity.getCar().getCarID())
                        .vin(entity.getCar().getVin())
                        .brand(entity.getCar().getBrand())
                        .model(entity.getCar().getModel())
                        .year(entity.getCar().getYear())
                        .gearbox(entity.getCar().getGearbox())
                        .build()
                )
                .build();
    }
}
