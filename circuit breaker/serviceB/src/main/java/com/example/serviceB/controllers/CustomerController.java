package com.example.serviceB.controllers;

import com.example.serviceB.models.Customer;
import com.example.serviceB.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;
    @GetMapping("/")
    public List<Customer> findAll(){
        return customerService.findAll();
    }
}
