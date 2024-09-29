package com.example.PaymentService.repositories;

import com.example.PaymentService.entites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
