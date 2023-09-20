package com.ecommers.entities;

import com.ecommers.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Payment")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "card_no")
    private String cardno;
    @Column(name = "cart_id")
    private int cartId;
    @Column(name = "status")
    private PaymentStatus Status;

//    @OneToOne
//    @JoinColumn(name = "cart_id")
//    private Cart cart;


}
