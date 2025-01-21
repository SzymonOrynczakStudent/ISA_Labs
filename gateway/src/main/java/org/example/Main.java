package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
        RouteLocatorBuilder builder,
        @Value("${rental.url}") String rentalUrl,
        @Value("${car.url}") String carUrl,
        @Value("${gateway.host}") String host

    ) {
        return builder
                .routes()
                .route("cars", route -> route
                    .host(host)
                    .and()
                    .path(
                        "/api/cars/{uuid}",
                        "/api/cars"
                    )
                    .uri(carUrl)
                )
                .route("rentals", route -> route
                        .host(host)
                        .and()
                        .path(
                            "/api/rentals",
                            "/api/rentals/**",
                            "/api/cars/{uuid}/rentals",
                                "/api/cars/{uuid}/rentals/**"
                        )
                        .uri(rentalUrl)
                )
                .build();
    }
}