package com.ecommers.dto;

import com.ecommers.entities.Payment;
import com.ecommers.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {

    @NotBlank
    private String Cardno;
    @NotNull
    private int cartid;
   // private int profileId;
   // private int payment_id;


    public PaymentDto(Payment payment) {
         //   this.id=payment.getId();
            this.Cardno=payment.getCardno();
            this.cartid=payment.getCart().getId();
//this.profileId=payment.getProfile().getId();
    }
}
