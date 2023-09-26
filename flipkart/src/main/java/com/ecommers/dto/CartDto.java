package com.ecommers.dto;

//import com.ecommers.entities.Cart;
import com.ecommers.entities.Cart;
import com.ecommers.entities.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

    //  private int id;
   private String name;

  //  private ItemDto itemDto;

   // private ProfileDto profileDto;

    public CartDto(Cart cart) {
       // this.itemId = cart.getItem().getId();
      //  this.profileId = cart.getItem().getId();
        //new ItemDto(cart.getItem());
        this.name=cart.getName();
    }
}
