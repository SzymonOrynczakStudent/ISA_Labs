package com.example.lab2.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car implements Serializable, Comparable<Car> {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private UUID carId;

    @EqualsAndHashCode.Include
    @Column(name = "vin", unique = true)
    private String vin;

    @EqualsAndHashCode.Include
    @Column(name = "brand")
    private String brand;

    @EqualsAndHashCode.Include
    @Column(name = "model")
    private String model;

    @EqualsAndHashCode.Include
    @Column(name = "production_year")
    private int year;

    @EqualsAndHashCode.Include
    @Column(name = "gearbox")
    private String gearbox;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<Rental> rentals;

    @Override
    public int compareTo(Car car) { return this.vin.compareTo(car.vin); }
}
