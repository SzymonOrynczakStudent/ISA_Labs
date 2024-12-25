package com.example.lab2.cmd;

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
                    System.out.println("Enter vin:");
                    String vin = scanner.next();
                    try {
                        System.out.println(writer.writeValueAsString(rentalController.getCarRentals(vin)));
                    } catch (NoSuchElementException e) {
                        System.out.println("No rental found");
                    }
                }
                case "get_rental" -> {
                    System.out.println("Enter rental id");
                    UUID uuid = UUID.fromString(scanner.next());
                    try {
                        System.out.println(writer.writeValueAsString(rentalController.getRental(uuid)));
                    } catch (NoSuchElementException e) {
                        System.out.println("Rental not found");
                    }
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
                case "put_rental" -> {
                    UUID uuid = UUID.randomUUID();
                    PutRentalRequest request = PutRentalRequest.builder()
                            .startDate(LocalDate.parse(scanner.next()))
                            .endDate(LocalDate.parse(scanner.next()))
                            .carId(UUID.fromString(scanner.next()))
                            .build();
                    try {
                        rentalController.putRental(uuid, request);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Bad request");
                    }
                }
                case "quit" -> {
                    break main_loop;
                }
            }
        }
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("help - Listing available commands");
        System.out.println("get_rentals - List all rentals (elements)");
        System.out.println("get_cars - List all cars (categories)");
        System.out.println("get_car_rentals - List all elements of a category (by vin)");
        System.out.println("put_rental - Add a new rental (car by id)");
        System.out.println("delete_rental - Delete an element by its id");
        System.out.println("quit - Exit the application");
    }
}
