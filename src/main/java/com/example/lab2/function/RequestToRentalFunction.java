package com.example.lab2.function;

import com.example.lab2.dto.GetCarsResponse;
import com.example.lab2.dto.PutRentalRequest;
import com.example.lab2.entities.Rental;
import com.example.lab2.entities.Car;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToRentalFunction implements BiFunction<UUID, PutRentalRequest, Rental> {

    @Override
    public Rental apply(UUID id, PutRentalRequest request) {
        return Rental.builder()
                .rentalId(id)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .car(Car.builder()
                        .carId(request.getCarId())
                        .build())
                .build();
    }
}
