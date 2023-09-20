package com.ecommers.entities;

import com.ecommers.dto.CartDto;
import com.ecommers.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    //    @Column(name = "item_id")
//    private  int itemId;
    @Column(name = "item_name")
    private String name;
//    @Column(name = "profile_id")
//    private int profileid;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "item_id")
    private Item item;

//    @OneToOne
//    @JsonIgnore
//    private Payment payment;

    public Cart(CartDto cartDto) {
        // this.id = cartDto.g;

        //  this.profileid =
        this.name=cartDto.getName();

    }
}
