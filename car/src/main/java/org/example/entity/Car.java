package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private UUID carId;

    @EqualsAndHashCode.Include
    @Column(name = "brand")
    private String brand;

    @EqualsAndHashCode.Include
    @Column(name = "vin", unique = true)
    private String vin;

    @EqualsAndHashCode.Include
    @Column(name = "model")
    private String model;

    @EqualsAndHashCode.Include
    @Column(name = "production_year")
    private int year;

    @EqualsAndHashCode.Include
    @Column(name = "gearbox")
    private String gearbox;
}