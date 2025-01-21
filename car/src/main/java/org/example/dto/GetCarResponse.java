package org.example.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCarResponse {

    private UUID id;
    private String vin;
    private String brand;
    private String model;
    private int year;
    private String gearbox;

}
