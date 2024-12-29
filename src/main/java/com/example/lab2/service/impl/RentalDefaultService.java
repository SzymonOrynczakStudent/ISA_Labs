package com.example.lab2.service.impl;

import com.example.lab2.entities.Rental;
import com.example.lab2.repository.CarRepository;
import com.example.lab2.repository.RentalRepository;
import com.example.lab2.service.api.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentalDefaultService implements RentalService {

    private final RentalRepository repository;

    private final CarRepository carRepository;

    @Autowired
    public RentalDefaultService(
            RentalRepository repository,
            CarRepository carRepository
    ){
        this.repository = repository;
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Rental> find(UUID rentalId) {
        return repository.findById(rentalId);
    }

    @Override
    public List<Rental> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(Rental rental) {
        repository.save(rental);
    }

    // TODO: naprawić tę funkcję tak, żeby nie trzeba było podawać carId w requeście.
    @Override
    public void update(Rental rental) { repository.save(rental); }

    @Override
    public void delete(UUID rentalId) {
        repository.findById(rentalId).ifPresent(repository::delete);
    }

    @Override
    public List<Rental> findAllByStartDate(LocalDate startDate) {
        return repository.findAllByStartDate(startDate);
    }

    @Override
    public List<Rental> findAllByEndDate(LocalDate endDate) {
        return repository.findAllByEndDate(endDate);
    }

    @Override
    public Optional<List<Rental>> findAllByCar(UUID carId) {
        return carRepository.findById(carId)
                .map(repository::findAllByCar);
    }

    @Override
    public Optional<List<Rental>> findAllByCar(String vin) {
        return carRepository.findByVin(vin)
                .map(repository::findAllByCar);
    }

    @Override
    public Optional<Rental> findById(UUID id) {
        return repository.findById(id);
    }
}
