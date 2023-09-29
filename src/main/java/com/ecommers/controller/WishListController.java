package com.ecommers.controller;

import com.ecommers.dto.CartDto;
import com.ecommers.dto.WishListDto;
import com.ecommers.service.WishListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<List<WishListDto>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.getall());
    }
    @PostMapping("/create")
    public ResponseEntity<WishListDto> create(@RequestBody @Valid WishListDto cartDto){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.create(cartDto));
    }
    @PutMapping("/update")
    public ResponseEntity<WishListDto> update(@RequestParam("id") int id,@RequestBody @Valid WishListDto wishListDto){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.update(id,wishListDto));
    }
    @DeleteMapping("/remove")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<WishListDto> remove(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(wishListService.remove(id));
    }
}
