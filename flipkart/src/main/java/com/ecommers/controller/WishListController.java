package com.ecommers.controller;

import com.ecommers.dto.CartDto;
import com.ecommers.dto.WishListDto;
import com.ecommers.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;


    @GetMapping("/all")
    public ResponseEntity<List<WishListDto>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.getall());
    }
    @PostMapping("/create")
    public ResponseEntity<WishListDto> create(@RequestBody WishListDto cartDto){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.create(cartDto));
    }
    @PutMapping("/update")
    public ResponseEntity<WishListDto> update(@RequestParam("id") int id,@RequestBody WishListDto wishListDto){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.update(id,wishListDto));
    }
    @DeleteMapping("/remove")
    public ResponseEntity<WishListDto> remove(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.remove(id));
    }
}
