package com.example.serviceA.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
public class ServiceAController {
    private final RestClient restClient;
    public ServiceAController(){
        restClient = RestClient.builder()
                .baseUrl("http://localhost:7070")
                .build();
    }
    //@CircuitBreaker(name = "serviceB", fallbackMethod = "execFallback")
    @GetMapping("/")
    public List<Customer> findAll(){
        return restClient.get()
                .uri("/")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
