package com.example.lab2.repository;

import com.example.lab2.entities.Car;
import com.example.lab2.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lab2.repository.CarRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {
    List<Rental> findAllByStartDate(LocalDate startDate);
    List<Rental> findAllByEndDate(LocalDate endDate);
    List<Rental> findAllByCar(Car car);
}
