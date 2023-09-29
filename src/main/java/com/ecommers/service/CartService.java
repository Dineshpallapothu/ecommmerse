package com.ecommers.service;

import com.ecommers.dto.CartDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

public interface CartService {

    List<CartDto> getall();

    CartDto remove(int id);

    List<CartDto> getcartbyprofile();

    List<CartDto> getcartbyitem(int id);
    List<CartDto> getcartbycategory(String name);
    CartDto create(CartDto cartDto);

    CartDto update(int id, CartDto cartDto);
    public void generateExcel(HttpServletResponse response) throws IOException;


}
