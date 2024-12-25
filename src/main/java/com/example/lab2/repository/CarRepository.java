package com.example.lab2.repository;

import com.example.lab2.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByBrand(String brand);
    List<Car> findByGearbox(String gearbox);
    List<Car> findByYear(int year);
    Optional<Car> findByVin(String vin);
}
