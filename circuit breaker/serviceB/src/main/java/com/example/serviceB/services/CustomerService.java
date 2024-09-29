package com.example.serviceB.services;

import com.example.serviceB.models.Customer;
import com.example.serviceB.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService implements ICustomerService{
    private final CustomerRepo customerRepo;
    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
}
