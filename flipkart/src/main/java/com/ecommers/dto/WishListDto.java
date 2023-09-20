package com.ecommers.dto;

//import com.ecommers.entities.WishList;
import com.ecommers.entities.WishList;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishListDto {
    private int itemId;
    private String name;
    private int profileId;

    public WishListDto(WishList wishList) {
        this.itemId = wishList.getItem().getId();
        this.name = wishList.getName();
        this.profileId= wishList.getProfile().getId();
    }
}
