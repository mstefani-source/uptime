package com.zmey.uptime.entities.enums;

import lombok.Data;

@Data
public class Payment {
    PaymentType type;

    public Payment(PaymentType type) {
        this.type = type;
    }
}
