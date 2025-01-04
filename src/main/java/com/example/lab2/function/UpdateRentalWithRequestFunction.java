package com.example.lab2.function;

import com.example.lab2.dto.PatchRentalRequest;
import com.example.lab2.entities.Car;
import com.example.lab2.entities.Rental;
import com.example.lab2.repository.RentalRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class UpdateRentalWithRequestFunction implements BiFunction<UUID, PatchRentalRequest, Rental> {

    private final RentalRepository rentalRepository;

    public UpdateRentalWithRequestFunction(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental apply(UUID id, PatchRentalRequest request) {
        // Fetch the existing rental from the repository to preserve any missing fields
        Rental existingRental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));

        Rental.RentalBuilder rentalBuilder = Rental.builder()
                .rentalId(id);

        // Preserve existing startDate if not provided
        if (request.getStartDate() != null) {
            rentalBuilder.startDate(request.getStartDate());
        } else {
            rentalBuilder.startDate(existingRental.getStartDate());  // Use existing startDate
        }

        // Preserve existing endDate if not provided
        if (request.getEndDate() != null) {
            rentalBuilder.endDate(request.getEndDate());
        } else {
            rentalBuilder.endDate(existingRental.getEndDate());  // Use existing endDate
        }

        // Handle carId if provided
        if (request.getCarId() != null) {
            rentalBuilder.car(Car.builder()
                    .carId(request.getCarId())
                    .build());
        } else {
            rentalBuilder.car(existingRental.getCar());  // Use existing car
        }

        return rentalBuilder.build();
    }
}

