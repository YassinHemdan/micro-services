package com.example.OrderService.command.api.events;

import com.example.CommonService.events.OrderCompletedEvent;
import com.example.OrderService.entities.Order;
import com.example.OrderService.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderEventsHandler {
    private OrderRepository orderRepository;
    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        Order order = new Order();
        BeanUtils.copyProperties(orderCreatedEvent, order);
        orderRepository.save(order);
    }
    @EventHandler
    public void on(OrderCompletedEvent event){
        Order order = orderRepository.findById(event.getOrderId()).get();
        order.setOrderStatus(event.getOrderStatus());
        orderRepository.save(order);
    }
}
