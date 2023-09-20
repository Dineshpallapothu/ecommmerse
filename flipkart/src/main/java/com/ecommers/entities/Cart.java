package com.ecommers.entities;

import com.ecommers.dto.CartDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

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
      //  this.id = cartDto.g;
        this.item.setId(cartDto.getItemId());
        this.profile.setId(cartDto.getProfileId());
      //  this.profileid =
        this.name=cartDto.getName();

    }
}
