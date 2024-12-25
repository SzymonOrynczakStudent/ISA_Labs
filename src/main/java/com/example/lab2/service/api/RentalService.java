package com.example.lab2.service.api;

import com.example.lab2.entities.Rental;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentalService {

    Optional<Rental> find(UUID rentalId);

    List<Rental> findAll();

    void create(Rental rental);

    void update(Rental rental);

    void delete(UUID rentalId);

    List<Rental> findAllByStartDate(LocalDate startDate);

    List<Rental> findAllByEndDate(LocalDate endDate);

    Optional<List<Rental>> findAllByCar(UUID carId);

    Optional<List<Rental>> findAllByCar(String vin);

    Optional<Rental> findById(UUID id);
}
