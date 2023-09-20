package com.ecommers.entities;

import com.ecommers.dto.WishListDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wishlist")
@Data
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    //    @Column(name = "item_id")
//    private int itemId;
    @Column(name = "item_name")
    private String name;
//    @Column(name = "profile_id")
//    private  int profileId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;

    @ManyToOne
    @JoinColumn(name ="profile_id")
    @JsonIgnore
    private Profile profile;


    public WishList(WishListDto wishListDto) {
        item.setId(wishListDto.getItemId());
        profile.setId(wishListDto.getProfileId());
//        this.itemId = wishListDto.getItemId();
        this.name = wishListDto.getName();
        // this.profileId = wishListDto.getProfileId();
    }
}
