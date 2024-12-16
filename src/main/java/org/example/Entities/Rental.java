package org.example.Entities;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode

public class Rental implements Comparable<Rental>, Serializable
{
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Car car;

    @Override
    public int compareTo(Rental RA) { return this.id.compareTo(RA.id); }
}