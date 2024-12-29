package com.example.lab2.function;

import com.example.lab2.dto.PatchRentalRequest;
import com.example.lab2.entities.Car;
import com.example.lab2.entities.Rental;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class UpdateRentalWithRequestFunction implements BiFunction<UUID, PatchRentalRequest, Rental> {

    @Override
    public Rental apply(UUID id, PatchRentalRequest request) {
        Rental.RentalBuilder rentalBuilder = Rental.builder()
                .rentalId(id)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate());

        if (request.getCarId() != null) {
            rentalBuilder.car(Car.builder()
                    .carId(request.getCarId())
                    .build());
        }

        return rentalBuilder.build();
    }
}
