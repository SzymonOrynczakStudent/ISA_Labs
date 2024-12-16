package org.example.Entities;

import lombok.*;

import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode

public class RentalDTO implements Comparable<RentalDTO>
{
    private Long id;
    private String vin;
    private Period duration;

    public RentalDTO(Rental rental)
    {
        this.id = rental.getId();
        this.vin = rental.getCar().getVin();
        this.duration = Period.between(rental.getStartDate(), rental.getEndDate());
    }

    @Override
    public int compareTo(RentalDTO r) { return this.id.compareTo(r.id);}

    @Override
    public String toString() {
        return String.format("RentalDTO{id=%d, vin='%s', duration='%d days'}",
                id,
                vin,
                duration.getDays());
    }
}