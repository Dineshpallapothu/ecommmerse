package com.ecommers.service;

import com.ecommers.dto.CartDto;
import com.ecommers.dto.ItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ItemService {
    List<ItemDto> getall();

    ItemDto  remove(int id);

    ItemDto create( ItemDto cartDto);

    List<ItemDto> getallbyName(String name);

    List<ItemDto> getllbyCat(String cat);

    ItemDto update( int id,ItemDto cartDto);
}
