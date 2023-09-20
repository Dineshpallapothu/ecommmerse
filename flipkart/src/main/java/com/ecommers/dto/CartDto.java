package com.ecommers.dto;

//import com.ecommers.entities.Cart;
import com.ecommers.entities.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

  //  private int id;
    private  int itemId;
    private int profileId;
    private String name;

    public CartDto(Cart cart) {
        this.itemId = cart.getItem().getId();
        this.profileId = cart.getItem().getId();
        this.name=cart.getName();
    }
}
