package com.example.ShipmentService.command.api.aggregates;

import com.example.CommonService.commands.ShipOrderCommand;
import com.example.CommonService.events.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShipmentAggregate {
    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    public ShipmentAggregate(){}
    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand command){
        // validate the command
        // publish the order shipped event

        OrderShippedEvent orderShippedEvent
                = OrderShippedEvent.builder()
                .shipmentId(command.getShipmentId())
                .orderId(command.getOrderId())
                .shipmentStatus("COMPLETED")
                .build();
        AggregateLifecycle.apply(orderShippedEvent);
    }
    @EventSourcingHandler
    public void on(OrderShippedEvent event){
        this.orderId = event.getOrderId();
        this.shipmentId = event.getShipmentId();
        this.shipmentStatus = event.getShipmentStatus();
    }
}
