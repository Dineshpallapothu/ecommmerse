package com.ecommers.entities;

import com.ecommers.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "order")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;
//    @Column(name = "item_id")
//    private  int itemId;
    @Column(name = "name")
    private String name;
//    @Column(name = "payment_id")
//    private int paymentId;
    @Column(name = "status")
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    @JsonIgnore
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;

//    @OneToOne
//    @JoinColumn(name = "payment_id")
//    private Payment payment;
}
