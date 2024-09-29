package com.example.ShipmentService.command.api.events;

import com.example.CommonService.events.OrderShippedEvent;
import com.example.ShipmentService.entites.Shipment;
import com.example.ShipmentService.repositories.ShipmentRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShipmentsEventHandler {
    private ShipmentRepository shipmentRepository;
    @EventHandler
    public void on(OrderShippedEvent event){
        Shipment shipment = new Shipment();
        BeanUtils.copyProperties(event, shipment);
        shipmentRepository.save(shipment);
    }
}
