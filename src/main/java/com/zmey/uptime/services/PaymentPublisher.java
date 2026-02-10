package com.zmey.uptime.services;

import java.util.List;

import com.zmey.uptime.entities.enums.Payment;
import com.zmey.uptime.entities.enums.PaymentNotificationService;
import com.zmey.uptime.entities.enums.PaymentType;

public class PaymentPublisher {
    private final List<PaymentNotificationService> services;

    public PaymentPublisher(List<PaymentNotificationService> services) {
        this.services = services;
    }

    public void publishPaymentCreationAsync(Payment payment) {
        // services.stream().filter(service -> service.supports(payment.getType()))
        // .findFirst()
        // .ifPresent(service-> service.notify(payment));
    
        PaymentType paymenType = payment.getType();
    
        for (PaymentNotificationService service : services) {
            if (service.supports(paymenType)) {
                service.notify(payment);
                return;
            }
        }

    }


}
