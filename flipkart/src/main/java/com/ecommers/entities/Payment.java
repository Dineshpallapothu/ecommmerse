package com.ecommers.entities;

import com.ecommers.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment" , schema = "public")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "card_no")
    private String cardno;
//    @Column(name = "cart_id")
//    private int cartId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus Status;

//    @Column(name = "profile_id")
//    private int profileid;

    @OneToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name ="profile_id", insertable = false,updatable = false)
    @JsonIgnore
    private Profile profile;


}
