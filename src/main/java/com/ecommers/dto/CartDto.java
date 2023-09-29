package com.ecommers.dto;

//import com.ecommers.entities.Cart;

import com.ecommers.entities.Cart;
import com.ecommers.entities.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

    //  private int id;
    @NotBlank
    private String name;
    @NotNull
    private Integer itemId;
    @NotNull
    private Integer profileId;

    public CartDto(Cart cart) {
        this.itemId = cart.getItem().getId();
        this.profileId = cart.getProfile().getId();
        this.name = cart.getName();
    }
}
