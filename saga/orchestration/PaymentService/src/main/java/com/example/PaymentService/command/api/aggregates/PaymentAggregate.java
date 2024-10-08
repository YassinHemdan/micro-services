package com.example.PaymentService.command.api.aggregates;

import com.example.CommonService.commands.ValidatePaymentCommand;
import com.example.CommonService.events.PaymentProcessedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class PaymentAggregate {
    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;
    public PaymentAggregate(){}

    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand){
        // validate payment details
        // public payment processed event
        log.info("executing validate payment command for " +
                "Order id: {} and payment id: {}",
                validatePaymentCommand.getOrderId(),
                validatePaymentCommand.getPaymentId());

        PaymentProcessedEvent paymentProcessedEvent
                = new PaymentProcessedEvent(
                    validatePaymentCommand.getPaymentId(),
                    validatePaymentCommand.getOrderId()
        );
        AggregateLifecycle.apply(paymentProcessedEvent);
        log.info("PaymentProcessedEvent Applied");
    }
    @EventSourcingHandler
    public void on(PaymentProcessedEvent event){
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
    }
}
