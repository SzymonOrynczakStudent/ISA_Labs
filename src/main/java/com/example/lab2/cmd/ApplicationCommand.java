package com.example.lab2.cmd;

import com.example.lab2.dto.PatchCarRequest;
import com.example.lab2.dto.PatchRentalRequest;
import com.example.lab2.dto.PutCarRequest;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.lab2.controller.api.RentalController;
import com.example.lab2.controller.api.CarController;
import com.example.lab2.dto.PutRentalRequest;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommand implements CommandLineRunner {

    private final RentalController rentalController;
    private final CarController carController;
    private final ObjectWriter writer;

    @Autowired
    public ApplicationCommand(
            RentalController rentalController,
            CarController carController,
            ObjectWriter writer
    ) {
        this.rentalController = rentalController;
        this.carController = carController;
        this.writer = writer;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        main_loop:
        while (true) {
            System.out.println("\nRental - element | Car - category");
            System.out.println("Enter a command (type 'help' for a list of commands):");
            command = scanner.next();

            switch (command) {
                case "help" -> {
                    printHelp();
                }
                case "get_rentals" -> {
                    System.out.println(writer.writeValueAsString(rentalController.getRentals()));
                }
                case "get_cars" -> {
                    System.out.println(writer.writeValueAsString(carController.getCars()));
                }
                case "get_car_rentals" -> {
                    System.out.println("Enter car id:");
                    UUID uuid = UUID.fromString(scanner.next());
                    try {
                        System.out.println(writer.writeValueAsString(rentalController.getCarRentals(uuid)));
                    } catch (NoSuchElementException e) {
                        System.out.println("No rental found");
                    }
                }
                case "get_rental" -> {
                    System.out.println("Enter rental id:");
                    UUID uuid = UUID.fromString(scanner.next());
                    try {
                        System.out.println(writer.writeValueAsString(rentalController.getRental(uuid)));
                    } catch (NoSuchElementException e) {
                        System.out.println("Rental not found");
                    }
                }
                case "get_car" -> {
                    System.out.println("Enter car vin:");
                    UUID id = UUID.fromString(scanner.next());
                    try {
                        System.out.println(writer.writeValueAsString(carController.getCar(id)));
                    } catch (NoSuchElementException e) {
                        System.out.println("Car not found");
                    }
                }
                case "update_rental" -> {
                    handleUpdateRental(scanner);
                }
                case "update_car" -> {
                    handleUpdateCar(scanner);
                }
                case "delete_rental" -> {
                    System.out.println("Enter rental id:");
                    UUID uuid = UUID.fromString(scanner.next());
                    try {
                        rentalController.deleteRental(uuid);
                    } catch (NoSuchElementException e) {
                        System.out.println("Rental not found");
                    }
                }
                case "delete_car" -> {
                    System.out.println("Enter car id:");
                    UUID uuid = UUID.fromString(scanner.next());
                    try {
                        carController.deleteCar(uuid);
                    } catch (NoSuchElementException e) {
                        System.out.println("Car not found");
                    }
                }
                case "put_rental" -> {
                    handlePutRental(scanner);
                }
                case "put_car" -> {
                    handlePutCar(scanner);
                }
                case "quit" -> {
                    System.exit(0);
                    break main_loop;
                }
            }
        }
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("help - Listing available commands");
        System.out.println("get_rentals - List all ids of rentals (elements)");
        System.out.println("get_cars - List all cars (categories)");
        System.out.println("get_car_rentals - List all elements of a category (by vin)");
        System.out.println("put_rental - Add a new rental (car by id)");
        System.out.println("put_car - Add a new car");
        System.out.println("update_rental - Update a rental (car by id)");
        System.out.println("update_Car - Update a car");
        System.out.println("delete_rental - Delete an element by its id");
        System.out.println("delete_car - Delete a car by id");
        System.out.println("quit - Exit the application");
    }

    private void handleUpdateRental(Scanner scanner) {
        System.out.println("Enter rental id:");
        UUID rentalId = UUID.fromString(scanner.next());
        System.out.println("Enter new start date (YYYY-MM-DD):");
        LocalDate startDate = LocalDate.parse(scanner.next());
        System.out.println("Enter new end date (YYYY-MM-DD):");
        LocalDate endDate = LocalDate.parse(scanner.next());
        System.out.println("Enter new carId:");
        UUID carId = UUID.fromString(scanner.next());

        PatchRentalRequest request = PatchRentalRequest.builder()
                .startDate(startDate)
                .endDate(endDate)
                .carId(carId)
                .build();
        try {
            rentalController.updateRental(rentalId, request);
        } catch (NoSuchElementException e) {
            System.out.println("Rental not found");
        }
    }

    private void handleUpdateCar(Scanner scanner) {
        System.out.println("Enter car id:");
        UUID carId = UUID.fromString(scanner.next());
        System.out.println("Enter car vin):");
        String vin = scanner.next();
        System.out.println("Enter car brand:");
        String brand = scanner.next();
        System.out.println("Enter car model:");
        String model = scanner.next();
        System.out.println("Enter car year:");
        String year = scanner.next();
        System.out.println("Enter car gearbox:");
        String gearbox = scanner.next();

        PatchCarRequest request = PatchCarRequest.builder()
                .vin(vin)
                .brand(brand)
                .model(model)
                .year(Integer.parseInt(year))
                .gearbox(gearbox)
                .build();
        try {
            carController.updateCar(carId, request);
        } catch (Exception e) {
            System.out.println("Car not found");
        }
    }

    private void handlePutRental(Scanner scanner) {
        System.out.println("Enter start date (YYYY-MM-DD):");
        LocalDate startDate = LocalDate.parse(scanner.next());
        System.out.println("Enter end date (YYYY-MM-DD):");
        LocalDate endDate = LocalDate.parse(scanner.next());
        System.out.println("Enter carId:");
        UUID carId = UUID.fromString(scanner.next());

        UUID id = UUID.randomUUID();
        PutRentalRequest request = PutRentalRequest.builder()
                .startDate(startDate)
                .endDate(endDate)
                .carId(carId)
                .build();
        try {
            rentalController.putRental(id, request);
        } catch (IllegalArgumentException e) {
            System.out.println("Bad request");
        }
    }

    private void handlePutCar(Scanner scanner) {
        System.out.println("Enter car vin:");
        String vin = scanner.next();
        System.out.println("Enter car brand:");
        String brand = scanner.next();
        System.out.println("Enter car model:");
        String model = scanner.next();
        System.out.println("Enter car year:");
        int year = Integer.parseInt(scanner.next());
        System.out.println("Enter car gearbox:");
        String gearbox = scanner.next();

        UUID id = UUID.randomUUID();
        PutCarRequest request = PutCarRequest.builder()
                .vin(vin)
                .brand(brand)
                .model(model)
                .year(year)
                .gearbox(gearbox)
                .build();

        try {
            carController.putCar(id, request);
        } catch (IllegalArgumentException e) {
            System.out.println("Bad request");
        }
    }
}
