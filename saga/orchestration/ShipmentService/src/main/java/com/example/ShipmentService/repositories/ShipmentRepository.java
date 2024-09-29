package com.example.ShipmentService.repositories;

import com.example.ShipmentService.entites.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, String> {
}
