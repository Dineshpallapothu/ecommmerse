package com.ecommers.controller;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.ProfileDto;
import com.ecommers.service.CartService;
import com.ecommers.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @GetMapping("/all")
    public ResponseEntity<List<ItemDto>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getall());
    }
    @GetMapping("/itemname")
    public ResponseEntity<List<ItemDto>> getbyname(@RequestParam("name") String name){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getallbyName(name));
    }
    @GetMapping("/category")
    public ResponseEntity<List<ItemDto>> getbycat(@RequestParam("cat") String cat){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getllbyCat(cat));
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ItemDto> create(@RequestBody @Valid ItemDto profile) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(profile));
    }
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ItemDto> update(@RequestParam("id") int id,@RequestBody @Valid ItemDto itemDto){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.update(id,itemDto));
    }
    @DeleteMapping("/remove")
    public ResponseEntity<ItemDto> remove(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.remove(id));
    }

}
