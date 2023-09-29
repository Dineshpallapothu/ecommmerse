package com.ecommers.dto;

//import com.ecommers.entities.Order;
import com.ecommers.entities.Order;
import com.ecommers.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

    @NotBlank
    private String name;
    @NotNull
    private int paymentid;
    // OrderStatus status;

    public OrderDto(Order order) {
     //   this.itemId = order.getItem().getId();
        this.name = order.getName();
            this.paymentid = order.getPayment().getId();
//        this.status = order.getStatus();
    }
}
