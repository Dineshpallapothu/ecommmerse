package com.ecommers.entities;

import com.ecommers.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<Cart> carts;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<WishList> wishLists;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<Order> orders;

    public Item(ItemDto itemDto) {
        this.name = itemDto.getName();
        category = itemDto.getCategory();
    }
}
