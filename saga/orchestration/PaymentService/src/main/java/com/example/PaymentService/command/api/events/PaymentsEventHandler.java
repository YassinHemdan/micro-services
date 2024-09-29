package com.example.PaymentService.command.api.events;

import com.example.CommonService.events.PaymentProcessedEvent;
import com.example.PaymentService.entites.Payment;
import com.example.PaymentService.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class PaymentsEventHandler {
    private PaymentRepository paymentRepository;

    @EventHandler
    public void on(PaymentProcessedEvent event){
        Payment payment = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("Completed")
                .timeStamp(new Date())
                .build();
        paymentRepository.save(payment);
    }
}
