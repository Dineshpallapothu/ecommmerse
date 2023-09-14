package com.ecommers.dto;

//import com.ecommers.entities.Order;
import com.ecommers.entities.Order;
import com.ecommers.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private  int itemId;
    private String name;
    private int paymentId;
    private OrderStatus status;

    public OrderDto(Order order) {
        this.itemId = order.getItemId();
        this.name = order.getName();
        this.paymentId = order.getPaymentId();
        this.status = order.getStatus();
    }
}
