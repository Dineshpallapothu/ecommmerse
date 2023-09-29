package com.ecommers.dto;

//import com.ecommers.entities.WishList;
import com.ecommers.entities.WishList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishListDto {
    @NotNull
    private int itemId;
    @NotBlank
    private String name;
    @NotNull
    private int profileId;

    public WishListDto(WishList wishList) {
        this.itemId = wishList.getItem().getId();
        this.name = wishList.getName();
        this.profileId= wishList.getProfile().getId();
    }
}
