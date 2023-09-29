package com.ecommers.controller;

import com.ecommers.dto.CartDto;
import com.ecommers.dto.ProfileDto;
import com.ecommers.service.CartService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<CartDto>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getall());
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<CartDto> create(@RequestBody @Valid CartDto cartDto){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.create(cartDto));
    }
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<List<CartDto>> getprofile(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getcartbyprofile());
    }
    @GetMapping("/item")
    public ResponseEntity<List<CartDto>> getitem(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getcartbyitem(id));
    }
    @GetMapping("/category")
    public ResponseEntity<List<CartDto>> getcat(@RequestParam("cat") String cat){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getcartbycategory(cat));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CartDto> update(@PathVariable("id") int id,@RequestBody @Valid CartDto cartDto){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.update(id,cartDto));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<CartDto> remove(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.remove(id));
    }
    @GetMapping("/generate")
    public  void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String filename="cart.xlsx";
        String headerkey="Content-Diposition";
        String headerValue="attachment; filename="+filename;
        response.setHeader(headerkey,headerValue);
        cartService.generateExcel(response);

    }

}
