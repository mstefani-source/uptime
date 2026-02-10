package com.zmey.uptime.entities.enums;

public interface PaymentNotificationService {
    void notify(Payment payment);
    boolean supports(PaymentType payment);   
}
