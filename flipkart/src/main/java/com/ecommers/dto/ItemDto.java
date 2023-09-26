package com.ecommers.dto;

//import com.ecommers.entities.Item;

import com.ecommers.entities.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto {
    // private  int id;
    private String name;
    private String Category;
    private int price;

    public ItemDto(Item item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.Category = item.getCategory();
    }

//    public ItemDto(ItemDto itemDto) {
//      //  this.id=itemDto.getId();
//        this.name=itemDto.getName();
//        this.Category=itemDto.getCategory();
//        this.price= itemDto.getPrice();
//    }
}
