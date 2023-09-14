package com.ecommers.service;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {
    List<OrderDto> getall();

    OrderDto  remove();

    OrderDto create( OrderDto orderDto);

    OrderDto update( OrderDto orderDto);
}
