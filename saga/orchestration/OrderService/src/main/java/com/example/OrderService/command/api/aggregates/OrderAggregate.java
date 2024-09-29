package com.example.OrderService.command.api.aggregates;

import com.example.CommonService.commands.CompleteOrderCommand;
import com.example.CommonService.events.OrderCompletedEvent;
import com.example.OrderService.command.api.commands.CreateOrderCommand;
import com.example.OrderService.command.api.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    public OrderAggregate(){}
    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand){
        // validate order command
        OrderCreatedEvent orderCreatedEvent
                = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        this.orderId = orderCreatedEvent.getOrderId();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
        this.addressId = orderCreatedEvent.getAddressId();
        this.quantity = orderCreatedEvent.getQuantity();
        this.productId = orderCreatedEvent.getProductId();
        this.userId = orderCreatedEvent.getUserId();
    }
    @CommandHandler
    public void handle(CompleteOrderCommand command){
        // validate command
        // publish order completed event
        OrderCompletedEvent orderCompletedEvent
                = OrderCompletedEvent.builder()
                .orderId(command.getOrderId())
                .orderStatus(command.getOrderStatus())
                .build();
        AggregateLifecycle.apply(orderCompletedEvent);
    }
    @EventSourcingHandler
    public void on(OrderCompletedEvent event){
        this.orderStatus = event.getOrderStatus();
    }
}
