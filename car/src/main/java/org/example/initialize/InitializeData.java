package org.example.initialize;

import org.example.entity.Car;
import org.example.service.api.CarService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class InitializeData implements InitializingBean {

    private final CarService carService;

    @Autowired
    public InitializeData(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void afterPropertiesSet() {
        Car ford = Car.builder()
                .carId(UUID.fromString("9d9d1636-aa96-4efc-9b37-4a4c27a3486f"))
                .vin("1HGCM82633A123456")
                .brand("Ford")
                .model("Focus")
                .year(2010)
                .gearbox("manual")
                .build();

        Car honda = Car.builder()
                .carId(UUID.fromString("9b3d0ce3-cd39-40ac-912a-646e5084ead0"))
                .vin("WDBRF40J93F123456")
                .brand("Honda")
                .model("Civic")
                .year(2011)
                .gearbox("manual")
                .build();

        Car suzuki = Car.builder()
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
    }
}
