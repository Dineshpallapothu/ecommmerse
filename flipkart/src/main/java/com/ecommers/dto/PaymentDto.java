package com.ecommers.dto;

import com.ecommers.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {
    private int itemId;
    private int paymentId;
    private PaymentStatus Status;
}
