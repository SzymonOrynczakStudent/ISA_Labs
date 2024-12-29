package com.example.lab2.controller.api;

import com.example.lab2.dto.GetCarResponse;
import com.example.lab2.dto.GetCarsResponse;
import com.example.lab2.dto.PatchCarRequest;
import com.example.lab2.dto.PutCarRequest;
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

    @PatchMapping("/api/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCar(
        @PathVariable("id")
        UUID id,
        @RequestBody PatchCarRequest request
    );

    @DeleteMapping("/api/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCar(
            @PathVariable("id")
            UUID id
    );

    @PutMapping("/api/cars/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putCar(
            @PathVariable("id")
            UUID id,
            @RequestBody PutCarRequest request
    );
}
