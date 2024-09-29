package com.example.customer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final RestTemplate restTemplate;
    @GetMapping("/get")
    public String get(){
        return restTemplate.getForObject("http://ORDER/get", String.class);
    }
}
