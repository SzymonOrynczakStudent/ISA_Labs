package com.example.lab2.initialize;

import com.example.lab2.entities.Car;
import com.example.lab2.entities.Rental;
import com.example.lab2.service.api.CarService;
import com.example.lab2.service.api.RentalService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

    private final RentalService rentalService;

    private final CarService carService;

    public InitializeData(RentalService rentalService, CarService carService) {
        this.rentalService = rentalService;
        this.carService = carService;
    }

    @Override
    public void afterPropertiesSet() {
        Car ford = Car.builder()
                //.carId(UUID.randomUUID())
                .carId(UUID.fromString("9d9d1636-aa96-4efc-9b37-4a4c27a3486f"))
                .vin("1HGCM82633A123456")
                .brand("Ford")
                .model("Focus")
                .year(2010)
                .gearbox("manual")
                .build();

        Car honda = Car.builder()
                //.carId(UUID.randomUUID())
                .carId(UUID.fromString("9b3d0ce3-cd39-40ac-912a-646e5084ead0"))
                .vin("WDBRF40J93F123456")
                .brand("Honda")
                .model("Civic")
                .year(2011)
                .gearbox("manual")
                .build();

        Car suzuki = Car.builder()
                //.carId(UUID.randomUUID())
                .carId(UUID.fromString("ed0707e5-fc53-4412-8221-4c50811816a0"))
                .vin("JHMFA36297S123456")
                .brand("Suzuki")
                .model("Celerio")
                .year(2007)
                .gearbox("manual")
                .build();

        carService.create(ford);
        carService.create(honda);
        carService.create(suzuki);

        Rental rental1 = Rental.builder()
                //.rentalId(UUID.randomUUID())
                .rentalId(UUID.fromString("15a21b67-675e-4b56-b0fb-326adfdfa421"))
                .startDate(LocalDate.parse("2024-07-17"))
                .endDate(LocalDate.parse("2024-07-20"))
                .car(ford)
                .build();

        Rental rental2 = Rental.builder()
                //.rentalId(UUID.randomUUID())
                .rentalId(UUID.fromString("a98a22f1-b7b4-4908-a8d3-d95071aba1c3"))
                .startDate(LocalDate.parse("2024-08-12"))
                .endDate(LocalDate.parse("2024-08-16"))
                .car(honda)
                .build();

        Rental rental3 = Rental.builder()
                //.rentalId(UUID.randomUUID())
                .rentalId(UUID.fromString("2d50af6e-53ff-49b2-a259-d29f4e594106"))
                .startDate(LocalDate.parse("2024-08-05"))
                .endDate(LocalDate.parse("2024-08-12"))
                .car(suzuki)
                .build();

        Rental rental4 = Rental.builder()
                //.rentalId(UUID.randomUUID())
                .rentalId(UUID.fromString("3d39d91d-4070-46f0-a579-d7375424739f"))
                .startDate(LocalDate.parse("2024-09-02"))
                .endDate(LocalDate.parse("2024-09-04"))
                .car(honda)
                .build();


        rentalService.create(rental1);
        rentalService.create(rental2);
        rentalService.create(rental3);
        rentalService.create(rental4);
    }
}
