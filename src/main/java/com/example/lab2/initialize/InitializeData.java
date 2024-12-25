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
                .carID(UUID.randomUUID())
                .vin("1HGCM82633A123456")
                .brand("Ford")
                .model("Focus")
                .year(2010)
                .gearbox("Manual")
                .build();

        Car honda = Car.builder()
                .carID(UUID.randomUUID())
                .vin("WDBRF40J93F123456")
                .brand("Honda")
                .model("Civic")
                .year(2011)
                .gearbox("Manual")
                .build();

        Car suzuki = Car.builder()
                .carID(UUID.randomUUID())
                .vin("JHMFA36297S123456")
                .brand("Suzuki")
                .model("Celerio")
                .year(2007)
                .gearbox("Manual")
                .build();

        carService.create(ford);
        carService.create(honda);
        carService.create(suzuki);

        Rental rental1 = Rental.builder()
                .rentalID(UUID.randomUUID())
                .startDate(LocalDate.parse("2024-07-17"))
                .endDate(LocalDate.parse("2024-07-20"))
                .car(ford)
                .build();

        Rental rental2 = Rental.builder()
                .rentalID(UUID.randomUUID())
                .startDate(LocalDate.parse("2024-08-12"))
                .endDate(LocalDate.parse("2024-08-16"))
                .car(honda)
                .build();

        Rental rental3 = Rental.builder()
                .rentalID(UUID.randomUUID())
                .startDate(LocalDate.parse("2024-08-05"))
                .endDate(LocalDate.parse("2024-08-12"))
                .car(suzuki)
                .build();

        Rental rental4 = Rental.builder()
                .rentalID(UUID.randomUUID())
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
