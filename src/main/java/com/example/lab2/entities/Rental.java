package com.example.lab2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental implements Serializable, Comparable<Rental> {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private UUID rentalID;

    @EqualsAndHashCode.Include
    @Column(name = "start_date")
    private LocalDate startDate;

    @EqualsAndHashCode.Include
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    @EqualsAndHashCode.Include
    private Car car;

    @Override
    public int compareTo(Rental r) { return this.rentalID.compareTo(r.rentalID); }
}