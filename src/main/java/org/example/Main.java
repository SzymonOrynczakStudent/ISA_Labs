package org.example;

import org.example.Entities.Car;
import org.example.Entities.Rental;
import org.example.Entities.RentalDTO;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        // Generating data
        List<Car> cars = generate_data();

        // TASK 2
        System.out.println("Task 2:");
        cars.forEach(Car -> {
            System.out.println(Car);
            Car.getRentals().forEach(System.out::println);
        });

        // TASK 3
        System.out.println("\nTask 3:");
        Set<Rental> rentals_recorded = cars.stream()
                .flatMap(car -> car.getRentals().stream())
                .collect(Collectors.toSet());
        rentals_recorded.forEach(System.out::println);

        //TASK 4
        System.out.println("\nTask 4:");
        rentals_recorded.stream()
                .filter(rental -> rental.getCar().getBrand().equals("Fiat"))
                .sorted(Comparator.comparing(rental -> rental.getCar().getGearbox()))
                .forEach(System.out::println);

        //TASK 5
        System.out.println("\nTask 5:");
        List<RentalDTO> rentals_dto = rentals_recorded.stream()
                .map(RentalDTO::new)
                .sorted()
                .toList();
        rentals_dto.forEach(System.out::println);

        //TASK 6
        System.out.println("\nTask 6:");
        String filename = "cars.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(cars);
            System.out.println("Car collection serialized successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            List<Car> deserialized_cars = (List<Car>) ois.readObject();
            System.out.println("Car collection deserialized successfully from " + filename);
            System.out.println("Deserialized Car collection:");
            deserialized_cars.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }

        //TASK 7
        System.out.println("\nTask 7:");
        ForkJoinPool custom_thread_pool = new ForkJoinPool(1);
        try
        {
            custom_thread_pool.submit(() ->
            cars.parallelStream().forEach(car -> {
                System.out.println(car);
                car.getRentals().forEach(rental -> {
                    System.out.println(rental);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            })).join();
        } finally {
            custom_thread_pool.shutdown();
        }

    }

    private static List<Car> generate_data()
    {
        List<Car> cars = new ArrayList<>();

        Car car1 = Car.builder()
                .vin("1HGCM82633A123456")
                .brand("Suzuki")
                .model("Celestio")
                .year(1998)
                .gearbox("Manual")
                .rentals(new ArrayList<>())
                .build();

        Car car2 = Car.builder()
                .vin("2T1BURHE2KC123456")
                .brand("Fiat")
                .model("Panda")
                .year(2012)
                .gearbox("Manual")
                .rentals(new ArrayList<>())
                .build();

        Car car3 = Car.builder()
                .vin("5YJ3E1EA9KF123456")
                .brand("Pegueot")
                .model("208")
                .year(2008)
                .gearbox("Manual")
                .rentals(new ArrayList<>())
                .build();

        Car car4 = Car.builder()
                .vin("1C4PJLCB1JD123456")
                .brand("Seat")
                .model("Arona")
                .year(2018)
                .gearbox("Automatic")
                .rentals(new ArrayList<>())
                .build();

        Rental rental1 = Rental.builder()
                .id(91456283472934562L)
                .startDate(LocalDate.parse("2024-06-16"))
                .endDate(LocalDate.parse("2024-06-20"))
                .car(car1)
                .build();
        car1.getRentals().add(rental1);

        Rental rental2 = Rental.builder()
                .id(91456283472934562L)
                .startDate(LocalDate.parse("2024-06-12"))
                .endDate(LocalDate.parse("2024-06-14"))
                .car(car2)
                .build();
        car2.getRentals().add(rental2);

        Rental rental3 = Rental.builder()
                .id(91456283472934562L)
                .startDate(LocalDate.parse("2024-06-21"))
                .endDate(LocalDate.parse("2024-06-25"))
                .car(car1)
                .build();
        car1.getRentals().add(rental3);

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);

        return cars;
    }

}

