package com.example.OrderService.command.api.saga;

import com.example.CommonService.commands.CompleteOrderCommand;
import com.example.CommonService.commands.ShipOrderCommand;
import com.example.CommonService.commands.ValidatePaymentCommand;
import com.example.CommonService.events.OrderCompletedEvent;
import com.example.CommonService.events.OrderShippedEvent;
import com.example.CommonService.events.PaymentProcessedEvent;
import com.example.CommonService.models.User;
import com.example.CommonService.queries.GetUserPaymentDetailsQuery;
import com.example.OrderService.command.api.events.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class OrderProcessingSaga {
    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent orderCreatedEvent){
        log.info("OrderCreatedEvent in saga for Order ID: {}",
                orderCreatedEvent.getOrderId());

        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery
                = new GetUserPaymentDetailsQuery(orderCreatedEvent.getUserId());
        User user = null;
        try {

            user = queryGateway.query(
                    getUserPaymentDetailsQuery,
                    ResponseTypes.instanceOf(User.class)
            ).join();
        }catch (Exception e){
            log.error(e.getMessage());

            // start the compensating transaction
        }
        ValidatePaymentCommand validatePaymentCommand
                = ValidatePaymentCommand.builder()
                .cardDetails(user.getCardDetails())
                .orderId(orderCreatedEvent.getOrderId())
                .paymentId(UUID.randomUUID().toString())
                .build();
        commandGateway.sendAndWait(validatePaymentCommand);
    }
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentProcessedEvent event){
        log.info("PaymentProcessedEvent in saga  for Order Id: {}",
                event.getOrderId());

        try {
            ShipOrderCommand shipOrderCommand
                    = ShipOrderCommand.builder()
                    .shipmentId(UUID.randomUUID().toString())
                    .orderId(event.getOrderId())
                    .build();
            commandGateway.sendAndWait(shipOrderCommand);
        }catch (Exception e){
            log.error(e.getMessage());
            // start the compensating transaction
        }
    }
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent event){
        log.info("OrderShippedEvent in Saga for Order Id: {}",
                event.getOrderId());

        CompleteOrderCommand command
                = CompleteOrderCommand.builder()
                .orderId(event.getOrderId())
                .orderStatus("APPROVED")
                .build();

        commandGateway.send(command);
    }
    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCompletedEvent event){
        log.info("OrderCompletedEvent in Saga for Order Id: {}",
                event.getOrderId());
    }
}
