package com.ecommers.service;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.WishListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface WishListService {
    List<WishListDto> getall();

    WishListDto  remove(int id);

    WishListDto create( WishListDto wishListDto);

    WishListDto update( int id,WishListDto wishListDto);
}
