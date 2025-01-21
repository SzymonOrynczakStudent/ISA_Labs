package org.example.controller.api;

import org.example.dto.GetCarResponse;
import org.example.dto.GetCarsResponse;
import org.example.dto.PatchCarRequest;
import org.example.dto.PutCarRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface CarController {

    @GetMapping("api/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCarsResponse getCars();

    @GetMapping("/api/cars/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCarResponse getCar(
            @PathVariable("id")
            UUID id
    );

    @DeleteMapping("/api/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCar(
            @PathVariable("id")
            UUID id
    );

    @PatchMapping("/api/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCar(
            @PathVariable("id")
            UUID id,
            @RequestBody PatchCarRequest request
    );

    @PutMapping("/api/cars/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putCar(
            @PathVariable("id")
            UUID id,
            @RequestBody PutCarRequest request
    );

}