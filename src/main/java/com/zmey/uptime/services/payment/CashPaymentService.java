package com.zmey.uptime.services.payment;

import com.zmey.uptime.entities.enums.PaymentType;
import com.zmey.uptime.entities.enums.Payment;
import com.zmey.uptime.entities.enums.PaymentNotificationService;

public class CashPaymentService implements PaymentNotificationService {

    @Override
    public void notify(Payment payment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notify'");
    }

    @Override
    public boolean supports(PaymentType paymentType) {
        return PaymentType.CASH.equals(paymentType);
    }
    
}