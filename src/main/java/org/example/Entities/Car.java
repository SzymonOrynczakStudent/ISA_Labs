package org.example.Entities;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "rentals")
@EqualsAndHashCode(exclude = "rentals")

public class Car implements Comparable<Car>, Serializable
{
    private String vin; // unique identifier
    private String brand;
    private String model;
    private int year;
    private String gearbox;
    List<Rental> rentals = new ArrayList<>();

    @Override
    public int compareTo(Car c) { return this.vin.compareTo(c.vin); }
}