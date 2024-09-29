package com.example.serviceB.services;

import com.example.serviceB.models.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
}
