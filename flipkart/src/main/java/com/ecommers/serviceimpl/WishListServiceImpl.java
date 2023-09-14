package com.ecommers.serviceimpl;

import com.ecommers.dto.CartDto;
import com.ecommers.dto.ItemDto;
import com.ecommers.dto.WishListDto;
//import com.ecommers.repositories.WishlistRepo;
import com.ecommers.entities.Cart;
import com.ecommers.entities.Item;
import com.ecommers.entities.Profile;
import com.ecommers.entities.WishList;
import com.ecommers.exceptions.InavalidDatagiven;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NothingFound;
import com.ecommers.repositories.ItemRepo;
import com.ecommers.repositories.ProfileRepo;
import com.ecommers.repositories.WishlistRepo;
import com.ecommers.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    WishlistRepo wishlistRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    ProfileRepo profileRepo;

    @Override
    public List<WishListDto> getall() {
        List<WishList> wishListLists = wishlistRepo.findAll();
        List<WishListDto> cartDtos = new ArrayList<>();
        if (wishListLists.isEmpty()) {
            throw new NoValueException("NO Items present");
        } else {
            for (WishList wishList : wishListLists) {
                WishListDto cartDto = new WishListDto(wishList);
                cartDtos.add(cartDto);
            }

        }

        return cartDtos;
    }

    @Override
    public WishListDto remove(int id) {
        WishList wishList = wishlistRepo.findById(id).orElseThrow(() -> new NothingFound("no items available in cart on this id"));
        WishListDto cartDto = new WishListDto(wishList);
        wishlistRepo.delete(wishList);
        return cartDto;
    }

    @Override
    public WishListDto create(WishListDto wishListDto) {
        Profile profile = profileRepo.findById(wishListDto.getProfileId()).orElseThrow(() -> new NothingFound("profile id not found can you check it"));
        Item item = itemRepo.findById(wishListDto.getItemId()).orElseThrow(() -> new NothingFound("item id not found can you check it"));
        if (wishListDto == null) {
            throw new InavalidDatagiven("pleae give correct data");
        } else {
            WishList wishList = new WishList(wishListDto);
            wishlistRepo.save(wishList);
        }
        return wishListDto;
    }

    @Override
    public WishListDto update(int id, WishListDto wishListDto) {
        WishList wishList = wishlistRepo.findById(id).orElseThrow(() -> new NothingFound("sorry"));
        if (wishList == null) {
            throw new NoValueException("NO Items present");
        } else {
            wishList.setItemId(wishListDto.getItemId());
            wishList.setName(wishListDto.getName());
            wishList.setProfileId(wishListDto.getProfileId());
            wishlistRepo.save(wishList);
        }
        return wishListDto;
    }
}
